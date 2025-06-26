package loja.estudo.spring;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.TestCase;
import loja.estudo.spring.controller.AcessoController;
import loja.estudo.spring.model.Acesso;
import loja.estudo.spring.repository.AcessoRepository;

@Profile("test")
@SpringBootTest(classes = LojaEstudoSpringApplication.class)
public class LojaEstudoSpringApplicationTests extends TestCase{/*Biblioteca JUnit3 baixada.*/

	@Autowired
	private AcessoController acessoController;
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	@Autowired
	private WebApplicationContext wac; // Para o objeto mockMvc
	
	@Test
	public void testRestApiCadastroAcesso() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build(); // objeto mock.
		
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_CADASTRO_ACESSO");
		/*Não precisa salvar, pois o mock fará isso pra gente.*/
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		ResultActions retornoApi = mockMvc
										.perform(MockMvcRequestBuilders.post("/salvarAcesso")
										.content(objectMapper.writeValueAsString(acesso))
										.accept(MediaType.APPLICATION_JSON)
										.contentType(MediaType.APPLICATION_JSON));
		
		System.out.println("Retorno da API: "+retornoApi.andReturn().getResponse().getContentAsString());
		
		Acesso acessoRetorno = objectMapper.readValue( retornoApi.andReturn().getResponse().getContentAsString(), 
				Acesso.class);
		
		assertEquals(acesso.getDescricao(), acessoRetorno.getDescricao());
		
	}
	
	@Test
	public void testRestApiDeleteAcesso() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
		
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_DELETADO");
		acessoRepository.save(acesso);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.post("/deleteAcesso")
										  .content(objectMapper.writeValueAsString(acesso))
										  .accept(MediaType.APPLICATION_JSON)
										  .contentType(MediaType.APPLICATION_JSON));
		
		System.out.println("Retorno da API: " + retornoApi.andReturn().getResponse().getContentAsString());
		System.out.println("Retorno do Status: " + retornoApi.andReturn().getResponse().getStatus());
		
		assertEquals("Acesso removido com sucesso", retornoApi.andReturn().getResponse().getContentAsString());
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
	}
	
	@Test
	public void testRestApiDeletePorIDAcesso() throws JsonProcessingException, Exception {
		
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
		
		Acesso acesso = new Acesso();
		acesso.setDescricao("");
		
	}
	
	@Test 
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














