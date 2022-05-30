package NTTDATA.AFP.api;

import NTTDATA.AFP.exception.ModelNotFoundException;
import NTTDATA.AFP.model.Customer;
import NTTDATA.AFP.service.AfpService;
import NTTDATA.AFP.service.CustomerAfpService;
import NTTDATA.AFP.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "customer")
public class CustomerApi {
    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerAfpService customerAfpService;

    @Autowired
    AfpService afpService;

    @GetMapping
    public ResponseEntity<List<Customer>> findAll(){
        return ResponseEntity.ok(customerService.findAll());
    }

    @PostMapping
    public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer){
        validate(customer);

        Customer response = customerService.create(customer);
        return new ResponseEntity<Customer>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Customer> update(@Valid @RequestBody Customer customer){
        validate(customer);

        Customer customerExists = customerService.findById(customer.getId());
        if(customerExists.getId() == 0){
            throw new ModelNotFoundException("ID not found.");
        }
        Customer response = customerService.update(customer);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable("id") long id){
        Customer customer = customerService.findById(id);
        if(customer.getId() == 0){
            throw new ModelNotFoundException("ID not found.");
        }
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id){
        Customer customer = customerService.findById(id);
        if(customer.getId() == 0){
            throw new ModelNotFoundException("ID not found.");
        }
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void validate(Customer customer){

        if(!afpService.existsById(customer.getIdAfp())) { throw new ModelNotFoundException("Id AFP does not exists."); }

        if(customerAfpService.existsByDni(customer.getDni())){ throw new ModelNotFoundException("DNI entered is already linked with an AFP."); }

        if(customerService.existsByDni(customer.getDni())){ throw new ModelNotFoundException("DNI entered is already linked with an AFP."); }
    }
}
