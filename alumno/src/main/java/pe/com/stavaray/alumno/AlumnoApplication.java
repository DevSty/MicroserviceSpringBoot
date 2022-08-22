package pe.com.stavaray.alumno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {
                "pe.com.stavaray.alumno",
                "pe.com.stavaray.alumnoqueues"
        }
)
@EnableFeignClients(
        basePackages = "pe.com.stavaray.alumnofeign")
@PropertySources({
        @PropertySource("classpath:alumnofeign-${spring.profiles.active}.properties")
})
public class AlumnoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlumnoApplication.class,args);
    }
}
