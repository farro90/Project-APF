package NTTDATA.AFP.repository;

import NTTDATA.AFP.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface RequestRepository extends JpaRepository<Request, Long> {
    boolean existsByDni(@RequestParam("dni") String dni);
}
