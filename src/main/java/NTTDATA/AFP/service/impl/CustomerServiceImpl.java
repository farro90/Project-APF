package NTTDATA.AFP.service.impl;

import NTTDATA.AFP.model.Customer;
import NTTDATA.AFP.repository.CustomerRepository;
import NTTDATA.AFP.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() { return customerRepository.findAll(); }

    @Override
    public Customer create(Customer persona) {
        return customerRepository.save(persona);
    }

    @Override
    public Customer update(Customer persona) {
        return customerRepository.save(persona);
    }

    @Override
    public Customer findById(long id) {
        Optional<Customer> op = customerRepository.findById(id);
        return op.isPresent() ? op.get() : new Customer();
    }

    @Override
    public boolean existsByDni(String dni) {
        return customerRepository.existsByDni(dni);
    }

    @Override
    public Customer findByDni(String dni) { return customerRepository.findByDni(dni); }

    @Override
    public void delete(long id) {
        customerRepository.deleteById(id);
    }
}
