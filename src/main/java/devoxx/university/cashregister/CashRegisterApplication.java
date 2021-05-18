package devoxx.university.cashregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaRepositories
@FlywayDataSource
@EnableSwagger2
public class CashRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashRegisterApplication.class, args);
	}

}
