package ma.enset.ensetrevisionspringang.repository;

import ma.enset.ensetrevisionspringang.entities.Payment;
import ma.enset.ensetrevisionspringang.entities.PaymentStatus;
import ma.enset.ensetrevisionspringang.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);
}
