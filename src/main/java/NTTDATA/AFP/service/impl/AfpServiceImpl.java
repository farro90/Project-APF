package NTTDATA.AFP.service.impl;

import NTTDATA.AFP.model.Afp;
import NTTDATA.AFP.model.Customer;
import NTTDATA.AFP.repository.AfpRepository;
import NTTDATA.AFP.repository.CustomerRepository;
import NTTDATA.AFP.service.AfpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AfpServiceImpl implements AfpService {

    @Autowired
    AfpRepository afpRepository;

    @Override
    public List<Afp> findAll() { return afpRepository.findAll(); }

    @Override
    public Afp findById(long id) {
        Optional<Afp> op = afpRepository.findById(id);
        return op.isPresent() ? op.get() : new Afp();
    }
}
