package com.reinaldo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reinaldo.domain.Pessoa;
import com.reinaldo.repositories.PessoaRepository;
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
	
	
	
	
}
