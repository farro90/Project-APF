package NTTDATA.AFP.repository;

import NTTDATA.AFP.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
