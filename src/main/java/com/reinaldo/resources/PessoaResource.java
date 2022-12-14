package com.reinaldo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.reinaldo.domain.Pessoa;
import com.reinaldo.domain.PessoaDTO;
import com.reinaldo.services.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaService service;

	@GetMapping("/{id}")
	public ResponseEntity<PessoaDTO> findByID(@PathVariable Integer id){
		Pessoa obj = service.findByID(id);
		PessoaDTO objDTO = new PessoaDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<PessoaDTO>> findAll(){
		List<PessoaDTO> list = service.findAll().stream().map(p -> new PessoaDTO(p)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}
	
	//Esse path recebe um objeto Pessoa, salva no banco como Pessoa, e retorna um objetoDTO
	@PostMapping
	public ResponseEntity<PessoaDTO> save(@RequestBody @Valid Pessoa p){
		PessoaDTO objDTO = service.save(p);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(objDTO.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PessoaDTO> update(@PathVariable Integer id, @Valid @RequestBody Pessoa pessoaObj){
		PessoaDTO objdDto = new PessoaDTO(service.update(id, pessoaObj));
		return ResponseEntity.ok().body(objdDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.notFound().build();
	}
	
	
}
