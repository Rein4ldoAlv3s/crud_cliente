package com.reinaldo.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.reinaldo.domain.Pessoa;
import com.reinaldo.domain.PessoaDTO;
import com.reinaldo.repositories.PessoaRepository;
import com.reinaldo.services.exceptions.DataIntegrityException;
import com.reinaldo.services.exceptions.NullPointerException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	public Pessoa findByID(Integer id) {
		Optional<Pessoa> obj = repository.findById(id);
		return obj.orElseThrow(() -> new NullPointerException
				("Objeto não encontrado! ID: " + id + " Objeto: " + Pessoa.class.getName()));
	}

	public List<Pessoa> findAll() {
		return repository.findAll();
	}

	

//	public PessoaDTO create(Pessoa pessoa) {
//		if(findByCPF(pessoa) != null) {
//			throw new DataIntegrityException("CPF já cadastrado na base de dados");
//		}
//		
//		Pessoa obj = repository.save(pessoa);
//		PessoaDTO objDTO = new PessoaDTO(obj);
//		return objDTO;
//	}
//	
//	
//	public Pessoa findByCPF(Pessoa p) {
//		Pessoa obj = repository.findByCPF(p.getCpf());
//		if(obj == null) {
//			return null;
//		}
//		return obj;
//	}
	
	
	
	public PessoaDTO save(Pessoa p) {
	 	Pessoa objCPF = findByCPF(p.getCpf());
		
	 	if(objCPF == null) {
	 		Pessoa obj = repository.save(p);
	 		PessoaDTO objDTO = new PessoaDTO(obj);
	 		return objDTO;
	 	}
	 	
	 	throw new DataIntegrityException("Informe um CPF diferente!");
		
	}
	
	public Pessoa findByCPF(String cpf) {
		Pessoa obj = repository.findByCPF(cpf);
		
		if(obj != null) {
			return obj;
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
