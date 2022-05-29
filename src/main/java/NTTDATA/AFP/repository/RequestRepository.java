package NTTDATA.AFP.repository;

import NTTDATA.AFP.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
