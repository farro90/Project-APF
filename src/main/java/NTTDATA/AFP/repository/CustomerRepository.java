package NTTDATA.AFP.repository;

import NTTDATA.AFP.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByDni(@RequestParam("dni") String dni);
}
