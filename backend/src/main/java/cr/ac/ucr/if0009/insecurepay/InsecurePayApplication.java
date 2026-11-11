package cr.ac.ucr.if0009.insecurepay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InsecurePayApplication {

    public static void main(String[] args) {
        SpringApplication.run(InsecurePayApplication.class, args);
        System.out.println("==========================================================");
        System.out.println(" InsecurePay Banking Portal - Backend Iniciado");
        System.out.println(" Servidor REST API: http://localhost:8080/api");
        System.out.println(" Consola H2 Web:    http://localhost:8080/h2-console");
        System.out.println(" JDBC URL:          jdbc:h2:mem:insecure_pay_db");
        System.out.println(" ADVERTENCIA:       Entorno vulnerable para Auditoria Forense");
        System.out.println("==========================================================");
    }
}

