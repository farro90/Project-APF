package NTTDATA.AFP.service.impl;

import NTTDATA.AFP.model.CustomerAfp;
import NTTDATA.AFP.model.Request;
import NTTDATA.AFP.model.bo.ValidateAmount;
import NTTDATA.AFP.repository.RequestRepository;
import NTTDATA.AFP.service.CustomerAfpService;
import NTTDATA.AFP.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    RequestRepository requestRepository;

    @Autowired
    CustomerAfpService customerAfpService;

    @Override
    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    @Override
    public Request create(Request request) {

        LocalDateTime localDateTime = LocalDateTime.now();
        Date dateToday = new Date(localDateTime.getYear(), localDateTime.getMonth().getValue(), localDateTime.getDayOfMonth(), localDateTime.getHour(), localDateTime.getMinute());
        request.setCreateDate(dateToday);
        request.setModifyDate(dateToday);

        return requestRepository.save(request);
    }

    @Override
    public Request update(Request request) {

        LocalDateTime localDateTime = LocalDateTime.now();
        Date dateToday = new Date(localDateTime.getYear(), localDateTime.getMonth().getValue(), localDateTime.getDayOfMonth(), localDateTime.getHour(), localDateTime.getMinute());
        request.setModifyDate(dateToday);

        return requestRepository.save(request);
    }

    @Override
    public Request findById(long id) {
        Optional<Request> op = requestRepository.findById(id);
        return op.isPresent() ? op.get() : new Request();
    }

    @Override
    public void delete(long id) {
        requestRepository.deleteById(id);
    }

    @Override
    public boolean validateAmount(ValidateAmount validateAmount) {
        CustomerAfp customerAfp = customerAfpService.findByDni(validateAmount.getDni());

        double amountPocertange = customerAfp.getAmount() / 2;//obtenerlo por porcentaje

        if(validateAmount.getAmount() > customerAfp.getAmount() || validateAmount.getAmount() < amountPocertange){
            return false;
        }
        return true;
    }
}
