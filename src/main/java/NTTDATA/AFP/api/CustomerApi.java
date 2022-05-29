package NTTDATA.AFP.api;

import NTTDATA.AFP.model.Customer;
import NTTDATA.AFP.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "customer")
public class CustomerApi {
    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> findAll(){
        return ResponseEntity.ok(customerService.findAll());
    }

    @PostMapping
    public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer){
        Customer response = customerService.create(customer);
        return new ResponseEntity<Customer>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Customer> update(@Valid @RequestBody Customer customer){
        Customer response = customerService.update(customer);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable("id") long id){
        Customer customer = customerService.findById(id);
        if(customer.getId() == 0){
            //throw new ModeloNotFoundException("ID no encontrado");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id){
        Customer customer = customerService.findById(id);
        if(customer.getId() == 0){
            //throw new ModeloNotFoundException("ID no encontrado");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
