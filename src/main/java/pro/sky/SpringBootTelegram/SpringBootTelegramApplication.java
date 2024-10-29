package pro.sky.SpringBootTelegram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootTelegramApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTelegramApplication.class, args);
	}

}
