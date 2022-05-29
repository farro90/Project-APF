package NTTDATA.AFP.service;

import NTTDATA.AFP.model.Request;
import NTTDATA.AFP.model.bo.ValidateAmount;

import java.util.List;

public interface RequestService {
    List<Request> findAll();

    Request create(Request request);

    Request update(Request request);

    Request findById(long id);

    void delete(long id);

    boolean validateAmount(ValidateAmount validateAmount);
}
