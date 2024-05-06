package ma.enset.ensetrevisionspringang.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder //lombok
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private double amount;
    private PaymentType  type;
    private PaymentStatus  status;
    private String file;
    // plusiers paiment pour un étudiant
    @ManyToOne
    private Student student;
}
