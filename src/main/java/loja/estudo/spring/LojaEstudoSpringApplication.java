package loja.estudo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "loja.estudo.spring.model")
@ComponentScan(basePackages = "loja.*")
@EnableJpaRepositories(basePackages = "loja.estudo.spring.repository")
@EnableTransactionManagement
public class LojaEstudoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaEstudoSpringApplication.class, args);
		System.out.println("Conexão loja virtual estudo.");
	}
}