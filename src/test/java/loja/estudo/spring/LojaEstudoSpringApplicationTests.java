package loja.estudo.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import loja.estudo.spring.controller.AcessoController;
import loja.estudo.spring.model.Acesso;
import loja.estudo.spring.repository.AcessoRepository;
import loja.estudo.spring.service.AcessoService;

@SpringBootTest(classes = LojaEstudoSpringApplication.class)
public class LojaEstudoSpringApplicationTests {

	@Autowired
	private AcessoController acessoController;
	
	@Autowired
	private AcessoService acessoService;
	
	@Test /*Anotação para rodar o teste.*/
	public void testeCadastraAcesso() {
		Acesso acesso = new Acesso();
		
		acesso.setDescricao("ROLE_ADMIN2");
		acessoController.salvarAcesso(acesso);	
	}
}