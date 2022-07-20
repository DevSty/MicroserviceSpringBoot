package pe.com.stavaray.alumno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(
        basePackages = "pe.com.stavaray.alumnofeign")
public class AlumnoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlumnoApplication.class,args);
    }
}
