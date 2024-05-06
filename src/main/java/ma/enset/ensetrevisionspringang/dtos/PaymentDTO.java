package ma.enset.ensetrevisionspringang.dtos;

import jakarta.persistence.ManyToOne;
import lombok.*;
import ma.enset.ensetrevisionspringang.entities.PaymentStatus;
import ma.enset.ensetrevisionspringang.entities.PaymentType;
import ma.enset.ensetrevisionspringang.entities.Student;

import java.time.LocalDate;

//ce n'est pas une entité JPA
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder //lombok
public class PaymentDTO {
    private Long id;
    private LocalDate date;
    private double amount;
    private PaymentType type;
    private PaymentStatus status;
    //private String file;
    // plusiers paiment pour un étudiant
    //private Student student;
    // le principe c'est au lieu de retourner les entités directement n va manipuler que les DTO
}
