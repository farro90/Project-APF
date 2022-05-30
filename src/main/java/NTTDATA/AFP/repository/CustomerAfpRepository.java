package NTTDATA.AFP.repository;

import NTTDATA.AFP.model.CustomerAfp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface CustomerAfpRepository extends JpaRepository<CustomerAfp, Long> {
    CustomerAfp findByDni(@RequestParam("dni") String dni);

    boolean existsByDni(@RequestParam("dni") String dni);

    boolean existsByDniAndAfp_id(@RequestParam("dni") String dni, @RequestParam("idAfp") long idAfp);
}
