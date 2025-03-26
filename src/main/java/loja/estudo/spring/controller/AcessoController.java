package loja.estudo.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import loja.estudo.spring.model.Acesso;
import loja.estudo.spring.repository.AcessoRepository;
import loja.estudo.spring.service.AcessoService;

@Controller
@RestController
public class AcessoController {
	
	@Autowired
	private AcessoService acessoService;
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	/*Salvar via requisição no corpo. Ok*/
	@PostMapping(value = "/salvarAcesso")
	@ResponseBody
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) {
		Acesso acessoSalvo = acessoService.save(acesso);
		return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
	}
	
	/*Deletar via resuisição no corpo. OK*/
	@PostMapping(value = "/deleteAcesso")
	@ResponseBody
	public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso) {
		acessoRepository.deleteById(acesso.getId());
		return new ResponseEntity<String>("Acesso removido com sucesso", HttpStatus.OK);
	}
	
	/*Deletar via ID. OK*/
	@ResponseBody
	@DeleteMapping(value = "/deleteAcessoPorId/{id}")
	public ResponseEntity<?> deleteAcessoPorId(@PathVariable("id") Long id){
		
		acessoRepository.deleteById(id);
		return new ResponseEntity<String>("Acesso removido pelo id.", HttpStatus.OK);
	}

	/*Obter acesso por id. OK*/
	@ResponseBody
	@GetMapping(value = "/obterAcesso/{id}")
	public ResponseEntity<?> obterAcesso(@PathVariable("id") Long id){
		
		Acesso acesso = acessoRepository.findById(id).get();
		return new ResponseEntity<Acesso>(acesso, HttpStatus.OK);
	}
	
	/*Buscar acesso com parte da String descricao.*/
	@ResponseBody
	@GetMapping(value = "/buscarPorDesc/{desc}")
	public ResponseEntity<?> buscarPorDesc(@PathVariable("desc") String desc){
		
		List<Acesso> acesso = acessoRepository.buscarAcessoDesc(desc);
		return new ResponseEntity<List<Acesso>>(acesso, HttpStatus.OK);
	}
	
}








