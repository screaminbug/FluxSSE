package hr.tstrelar.fluxsse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FluxSseApplication {

    public static void main(String[] args) {
        SpringApplication.run(FluxSseApplication.class, args);
    }

}
