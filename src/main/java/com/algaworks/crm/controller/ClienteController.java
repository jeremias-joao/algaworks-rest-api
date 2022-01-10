package com.algaworks.crm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.crm.model.ClienteEntity;
import com.algaworks.crm.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@GetMapping(value = "/listartodos") 
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<ClienteEntity>listar (ClienteEntity clienteEntity) {
		return clienteRepository.findAll();
	}
	
	//sera por cpf
	@GetMapping(value = "/buscaporid/{id}") 
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Optional<ClienteEntity> listarPorId (@PathVariable Long id){
		
		try {
			return clienteRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	@PostMapping(value = "/salvar") 
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteEntity adicionar(@RequestBody ClienteEntity cliente) {
		
		return clienteRepository.save(cliente);
	}
	
	@PutMapping(value="/atualizar/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> atualizar (@RequestBody ClienteEntity cliente) {
		if (cliente.getId() == null) {
			return new ResponseEntity<String>("Cliente não Encontrado !!!", HttpStatus.BAD_REQUEST);
		}
		ClienteEntity clienteNovo = clienteRepository.saveAndFlush(cliente);
		
		return new ResponseEntity<ClienteEntity>(clienteNovo, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/deletar/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String>delete(@PathVariable Long id) {
		clienteRepository.deleteById(id);
		 
		return new ResponseEntity<String>("Cliente deletado com sucesso", HttpStatus.OK);
	}
	
}
