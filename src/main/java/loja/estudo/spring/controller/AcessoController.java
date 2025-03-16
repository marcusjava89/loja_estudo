package loja.estudo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	@PostMapping(value = "/salvarAcesso")
	@ResponseBody
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) {
		Acesso acessoSalvo = acessoService.save(acesso);
		return new ResponseEntity<Acesso>(acessoSalvo,HttpStatus.OK);
	}
	
	@PostMapping(value = "/deleteAcesso")
	@ResponseBody
	public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso) {
		acessoRepository.deleteById(acesso.getId());
		return new ResponseEntity<String>("Acesso removido com sucesso",HttpStatus.OK);
	}

}








