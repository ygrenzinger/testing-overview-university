package devoxx.university.cashregister;

import devoxx.university.cashregister.domain.CashRegister;
import devoxx.university.cashregister.domain.discount.DiscountStore;
import devoxx.university.cashregister.domain.fruit.FruitPrice;
import devoxx.university.cashregister.infrastructure.FruitPriceRepository;
import devoxx.university.cashregister.infrastructure.FruitRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public CashRegister cashRegister(FruitPrice fruitPrice, DiscountStore discountStore) {
        return new CashRegister(fruitPrice, discountStore);
    }

}
