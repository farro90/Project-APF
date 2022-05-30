package NTTDATA.AFP.api;

import NTTDATA.AFP.exception.ModelNotFoundException;
import NTTDATA.AFP.model.Request;
import NTTDATA.AFP.model.bo.ValidateAfp;
import NTTDATA.AFP.model.bo.ValidateAmount;
import NTTDATA.AFP.service.AfpService;
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

    @Autowired
    AfpService afpService;

    @GetMapping
    public ResponseEntity<List<Request>> findAll(){
        return ResponseEntity.ok(requestService.findAll());
    }

    @PostMapping
    public ResponseEntity<Request> create(@Valid @RequestBody Request request){

        validate(request);

        Request response = requestService.create(request);
        log.info("Request registered successfully.");
        return new ResponseEntity<Request>(response, HttpStatus.CREATED);

    }

    @PutMapping
    public ResponseEntity<Request> update(@Valid @RequestBody Request request){

        Request requestExists = requestService.findById(request.getId());
        if(requestExists.getId() == 0){ throw new ModelNotFoundException("ID not found."); }

        validate(request);

        Request response = requestService.update(request);
        log.info("Request updated successfully.");
        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> findById(@PathVariable("id") long id){
        Request request = requestService.findById(id);
        if(request.getId() == 0){
            throw new ModelNotFoundException("ID not found.");
        }
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id){
        Request request = requestService.findById(id);
        if(request.getId() == 0){
            throw new ModelNotFoundException("ID not found.");
        }
        requestService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void validate(Request request){

        if(!afpService.existsById(request.getIdAfp())) { throw new ModelNotFoundException("Id AFP does not exists."); }

        if(requestService.existsByDni(request.getDni())){ throw new ModelNotFoundException("There is already a request with this DNI."); }

        if(!customerAfpService.existsByDni(request.getDni())){ throw new ModelNotFoundException("DNI not found."); }

        ValidateAfp validateAfp = new ValidateAfp(request.getDni(), request.getIdAfp());
        if(!customerAfpService.validateAfp(validateAfp)){ throw new ModelNotFoundException("DNI does not belong to this AFP."); }

        ValidateAmount validateAmount = new ValidateAmount(request.getDni(), request.getAmount());
        short responseValidateAmount = requestService.validateAmount(validateAmount);
        if(responseValidateAmount == 1){ throw new ModelNotFoundException("The request cannot be registered. Amount greater than allowed."); }

        if(responseValidateAmount == -1){ throw new ModelNotFoundException("Minimum amount not covered please check the minimum amount to withdraw."); }
    }
}
