package NTTDATA.AFP.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Schema(description = "Afp information")
@Data
@Entity
@Table(name = "Afp")
public class Afp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Afp name")
    @Size(min = 3, max = 70, message = "Afp name must have at least 3 characters.")
    @Column(name = "name", nullable = false, length = 70)
    private String name;
}
