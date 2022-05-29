package NTTDATA.AFP.api;

import NTTDATA.AFP.exception.ModelNotFoundException;
import NTTDATA.AFP.model.Afp;
import NTTDATA.AFP.model.Customer;
import NTTDATA.AFP.service.AfpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "afp")
public class AfpApi {
    @Autowired
    AfpService afpService;

    @GetMapping
    public ResponseEntity<List<Afp>> findAll(){
        return ResponseEntity.ok(afpService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Afp> findById(@PathVariable("id") long id){
        Afp afp = afpService.findById(id);
        if(afp.getId() == 0){
            throw new ModelNotFoundException("ID not found.");
        }
        return ResponseEntity.ok(afp);
    }
}
