package org.demo.parcialBackend.entities;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name="Dna")
public class Dna implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @Column(name = "dna")
    private String dna;
    @Column(name = "isMutant")
    private boolean isMutant;
}
