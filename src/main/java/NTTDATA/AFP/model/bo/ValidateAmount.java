package NTTDATA.AFP.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidateAmount {
    private String dni;
    private double amount;
}
