package NTTDATA.AFP.service;

import NTTDATA.AFP.model.Afp;
import NTTDATA.AFP.model.Customer;

import java.util.List;

public interface AfpService {
    List<Afp> findAll();

    Afp findById(long id);
}
