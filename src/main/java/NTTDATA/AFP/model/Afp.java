package NTTDATA.AFP.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "Afp")
public class Afp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@Schema(description = "Afp name")
    @Size(min = 3, max = 70, message = "Afp name must have at least 3 characters.")
    @Column(name = "name", nullable = false, length = 70)
    private String name;
}
