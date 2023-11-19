package kitri.dev6.memore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class MemoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(MemoreApplication.class, args);
	}
}
