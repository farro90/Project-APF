package NTTDATA.AFP.repository;

import NTTDATA.AFP.model.Afp;
import NTTDATA.AFP.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface AfpRepository extends JpaRepository<Afp, Long> {
    boolean existsById(@RequestParam("id") long id);
}
