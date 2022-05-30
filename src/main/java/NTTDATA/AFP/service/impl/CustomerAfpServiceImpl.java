package NTTDATA.AFP.service.impl;

import NTTDATA.AFP.model.CustomerAfp;
import NTTDATA.AFP.model.bo.ValidateAfp;
import NTTDATA.AFP.repository.CustomerAfpRepository;
import NTTDATA.AFP.service.CustomerAfpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerAfpServiceImpl implements CustomerAfpService {

    @Autowired
    CustomerAfpRepository customerAfpRepository;

    @Override
    public CustomerAfp findByDni(String dni) {
        return customerAfpRepository.findByDni(dni);
    }

    @Override
    public boolean existsByDni(String dni) {
        return customerAfpRepository.existsByDni(dni);
    }

    @Override
    public boolean validateAfp(ValidateAfp validateAfp) {
        return customerAfpRepository.existsByDniAndAfp_id(validateAfp.getDni(), validateAfp.getIdAfp());
    }
}
