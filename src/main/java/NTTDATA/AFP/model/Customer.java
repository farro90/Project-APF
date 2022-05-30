package NTTDATA.AFP.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//@Schema(description = "Información de Persona")
@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@Schema(description = "Customer names")
    @Size(min = 3, max = 70, message = "Customer names must have at least 3 characters.")
    @Column(name = "names", nullable = false, length = 70)
    private String names;

    //@Schema(description = "Customer last names")
    @Size(min = 3, max = 70, message = "Last name must have at least 3 characters.")
    @Column(name = "lastnames", nullable = false, length = 70)
    private String lastnames;

    //@Schema(description = "Customer DNI")
    @Pattern(regexp = "^[0-9]*$", message = "DNI must contain only numeric digits.")
    @Size(min = 8, max = 8, message = "DNI must have 8 digits.")
    @Column(name = "dni", nullable = false, length = 8, unique = true)
    private String dni;

    //@Schema(description = "Customer phone")
    @Size(min = 9, max = 15, message = "Email must have at least 9 digits.")
    @Column(name = "phone", nullable = false, length = 70)
    private String phone;

    //@Schema(description = "Customer email")
    @Size(min = 3, max = 70, message = "Email must have at least 3 characters.")
    @Column(name = "email", nullable = false, length = 70)
    private String email;

    //@Schema(description = "Id AFP")
    @Column(name = "idAfp", nullable = false)
    private int idAfp;

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
