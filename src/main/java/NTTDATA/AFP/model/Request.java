package NTTDATA.AFP.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

//@Schema(description = "Información de Request")
@Data
@Entity
@Table(name = "Requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@Schema(description = "Customer DNI")
    @Pattern(regexp = "^[0-9]*$", message = "DNI must contain only numeric digits.")
    @Size(min = 8, max = 8, message = "DNI must have 8 digits.")
    @Column(name = "dni", nullable = false, length = 8, unique = true)
    private String dni;

    //@Schema(description = "Amount")
    @Column(name = "amount", nullable = false)
    private double amount;

    //@Schema(description = "id AFP")
    @Column(name = "idAfp", nullable = false)
    private int idAfp;

    //@Schema(description = "createDate")
    @Column(name = "createDate")
    private Date createDate;

    //@Schema(description = "modifyDate")
    @Column(name = "modifyDate")
    private Date modifyDate;
}
