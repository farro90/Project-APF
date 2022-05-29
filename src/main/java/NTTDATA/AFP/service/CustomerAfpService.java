package NTTDATA.AFP.service;

import NTTDATA.AFP.model.CustomerAfp;
import NTTDATA.AFP.model.bo.ValidateAfp;

public interface CustomerAfpService {
    CustomerAfp findByDni(String dni);
    boolean existsByDni(String dni);
    boolean validateAfp(ValidateAfp validateAfp);
}
