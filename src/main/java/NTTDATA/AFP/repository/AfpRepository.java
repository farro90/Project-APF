package NTTDATA.AFP.repository;

import NTTDATA.AFP.model.Afp;
import NTTDATA.AFP.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AfpRepository extends JpaRepository<Afp, Long> {
}
