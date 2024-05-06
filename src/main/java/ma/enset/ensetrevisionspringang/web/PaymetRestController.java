package ma.enset.ensetrevisionspringang.web;

import ma.enset.ensetrevisionspringang.entities.Payment;
import ma.enset.ensetrevisionspringang.entities.PaymentStatus;
import ma.enset.ensetrevisionspringang.entities.PaymentType;
import ma.enset.ensetrevisionspringang.entities.Student;
import ma.enset.ensetrevisionspringang.repository.PaymentRepository;
import ma.enset.ensetrevisionspringang.repository.StudentRepository;
import ma.enset.ensetrevisionspringang.services.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class PaymetRestController {
    // pour chaque methode du contoleur vous avez 2 objets un objet request DTO, et un objet respense DTO
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;
    private PaymentService paymentService;

    //l'injection
    public PaymetRestController(StudentRepository studentRepository, PaymentRepository paymentRepository, PaymentService paymentService) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
    }

    @GetMapping(path = "/payments")
    public List<Payment> allPayments(){
        return paymentRepository.findAll();
    }

    @GetMapping(path = "/students/{code}/payments")
    public List<Payment> paymentsByStudent(@PathVariable String code){
        return paymentRepository.findByStudentCode(code);
    }

    @GetMapping(path = "/payments/byStatus")
    public List<Payment> paymentsByStatus(@RequestParam PaymentStatus status){
        return paymentRepository.findByStatus(status);
    }

    @GetMapping(path = "/payments/byType")
    public List<Payment> paymentsByType(@RequestParam PaymentType type){
        return paymentRepository.findByType(type);
    }

    @GetMapping(path = "/payments/{id}")
    public Payment getPaymentById(@PathVariable Long id){
        return paymentRepository.findById(id).get();
    }

    @GetMapping(path = "/students")
    public List<Student> allStudents(){
        return studentRepository.findAll();
    }

    @GetMapping(path = "/students/{code}")
    public Student getStudentByCode(@PathVariable String code){
        return studentRepository.findByCode(code);
    }

    @GetMapping(path = "/studentsByProgramId")
    public List<Student> getStudentByProgramId(@RequestParam String programId){
        return studentRepository.findByProgramId(programId);
    }

    @PutMapping(path = "/payments/{id}")
    public Payment updatePaymentStatus(@RequestParam PaymentStatus status,@PathVariable Long id){
        return paymentService.updatePaymentStatus(status,id);
    }

    //to upload file => MultipartFile file
    @PostMapping(path ="/payments",consumes = MediaType.MULTIPART_FORM_DATA_VALUE) // les données ils contient plusieurs partes : fichies, données structurées,...
    public Payment savePayment(@RequestParam MultipartFile file, LocalDate date, double amount, PaymentType type,
                                String studentCode) throws IOException {
       return this.paymentService.savePayment(file,date,amount,type,studentCode);
    }

    // Methode qui permet de consulter un fichier
    //http://localhost:8021/paymentFile/41
    @GetMapping(path = "/paymentFile/{paymentId}",produces = MediaType.APPLICATION_PDF_VALUE)//indique au navigateur que c'est données est un fichier PDF
    public byte[] getPaymentFile(@PathVariable Long paymentId) throws IOException {
        return paymentService.getPaymentFile(paymentId);
    }

}
