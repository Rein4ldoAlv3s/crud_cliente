package com.reinaldo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
