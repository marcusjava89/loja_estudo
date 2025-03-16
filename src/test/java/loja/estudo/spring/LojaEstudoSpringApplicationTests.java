package loja.estudo.spring;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import junit.framework.TestCase;
import loja.estudo.spring.controller.AcessoController;
import loja.estudo.spring.model.Acesso;
import loja.estudo.spring.repository.AcessoRepository;
import loja.estudo.spring.service.AcessoService;

@SpringBootTest(classes = LojaEstudoSpringApplication.class)
public class LojaEstudoSpringApplicationTests extends TestCase{/*Biblioteca JUnit3 baixada.*/

	@Autowired
	private AcessoController acessoController;
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	@Test /*Anotação para rodar o teste.*/
	public void testeCadastraAcesso() {
		
		/*Teste de salvar.*/
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_TEST");
		assertEquals(true, acesso.getId() == null);
		
		acessoController.salvarAcesso(acesso).getBody();
		
		assertEquals(true, acesso.getId()>0);
		
		/*Teste de carregamento.*/
		Acesso acesso2 = acessoRepository.findById(acesso.getId()).get();
		assertEquals(acesso.getId(), acesso2.getId());
		
		/*Teste de delete*/
		acessoRepository.deleteById(acesso2.getId());
		acessoRepository.flush();
		
		Acesso acesso3 = acessoRepository.findById(acesso2.getId()).orElse(null);
		assertEquals(true, acesso3 == null);
		
		/*Teste de query*/
		acesso = new Acesso();
		acesso.setDescricao("ROLE_ALUNO");
		acessoController.salvarAcesso(acesso).getBody();
		
		List<Acesso> acessos = acessoRepository.buscarAcessoDesc("ALUNO");
		assertEquals(true, acessos.size() == 1);
		
		acessoRepository.deleteById(acesso.getId());
		
		
		
	}
}














