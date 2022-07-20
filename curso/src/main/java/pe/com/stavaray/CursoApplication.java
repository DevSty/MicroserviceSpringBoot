package pe.com.stavaray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(
        basePackages = "pe.com.stavaray.alumnofeign"
)
public class CursoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CursoApplication.class,args);
    }
}

