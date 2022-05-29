package NTTDATA.AFP.api;

import NTTDATA.AFP.model.Request;
import NTTDATA.AFP.model.bo.ValidateAfp;
import NTTDATA.AFP.model.bo.ValidateAmount;
import NTTDATA.AFP.service.CustomerAfpService;
import NTTDATA.AFP.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "request")
public class RequestApi {
    @Autowired
    RequestService requestService;

    @Autowired
    CustomerAfpService customerAfpService;

    @GetMapping
    public ResponseEntity<List<Request>> findAll(){
        return ResponseEntity.ok(requestService.findAll());
    }

    @PostMapping
    public ResponseEntity<Request> create(@Valid @RequestBody Request request){

        if(!requestService.existsByDni(request.getDni())){
            if(customerAfpService.existsByDni(request.getDni())){
                ValidateAfp validateAfp = new ValidateAfp(request.getDni(), request.getIdAfp());
                if(customerAfpService.validateAfp(validateAfp)){
                    ValidateAmount validateAmount = new ValidateAmount(request.getDni(), request.getAmount());
                    short responseValidateAmount = requestService.validateAmount(validateAmount);
                    if(responseValidateAmount == 1){
                        log.info("The request cannot be registered. Amount greater than allowed.");
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    }else if(responseValidateAmount == -1){
                        log.info("Minimum amount not covered please check the minimum amount to withdraw.");
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    }else{
                        Request response = requestService.create(request);
                        return new ResponseEntity<Request>(response, HttpStatus.CREATED);
                    }
                }else{
                    log.info("DNI does not belong to this AFP.");
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }

            }else{
                log.info("DNI not found.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else{
            log.info("There is already a request with this DNI.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Request> update(@Valid @RequestBody Request request){
        Request requestExists = requestService.findById(request.getId());
        if(request.getId() == 0){
            //throw new ModeloNotFoundException("ID no encontrado");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Request response = requestService.update(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> findById(@PathVariable("id") long id){
        Request request = requestService.findById(id);
        if(request.getId() == 0){
            //throw new ModeloNotFoundException("ID no encontrado");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id){
        Request request = requestService.findById(id);
        if(request.getId() == 0){
            //throw new ModeloNotFoundException("ID no encontrado");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        requestService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
