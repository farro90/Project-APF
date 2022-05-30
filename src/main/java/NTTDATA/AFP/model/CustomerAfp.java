package NTTDATA.AFP.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

//@Schema(description = "Información de Persona")
@Data
@Entity
@Table(name = "customersafp")
public class CustomerAfp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@Schema(description = "Customer DNI")
    @Pattern(regexp = "^[0-9]*$", message = "DNI must contain only numeric digits.")
    @Size(min = 8, max = 8, message = "DNI must have 8 digits.")
    @Column(name = "dni", nullable = false, length = 8, unique = true)
    private String dni;

    //@Schema(description = "Id AFP")
    @Column(name = "idAfp", nullable = false)
    private int idAfp;

    //@Schema(description = "Amount Available")
    @Column(name = "amountAvailable", nullable = false)
    private double amount;

    //@Schema(description = "Retirement Date")
    @Column(name = "retirementDate")
    private Date retirementDate;

    //@Schema(description = "Account Number")
    @Column(name = "accountNumber", nullable = false)
    private String accountNumber;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "afp_id", nullable = false)
    private Afp afp;

    public Afp getAfp() {
        return afp;
    }

    public void setAfp(Afp afp) {
        this.afp = afp;
    }
}
