package com.reinaldo.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
		return obj.orElseThrow(() -> new NullPointerException(
				"Objeto não encontrado! ID: " + id + " Objeto: " + Pessoa.class.getName()));
	}

	public List<Pessoa> findAll() {
		return repository.findAll();
	}

	public PessoaDTO save(Pessoa p) {
		Pessoa objCPF = findByCPF(p.getCpf());

		if (objCPF == null) {
			Pessoa obj = repository.save(p);
			PessoaDTO objDTO = new PessoaDTO(obj);
			return objDTO;
		}

		throw new DataIntegrityException("Informe um CPF diferente!");

	}

	public Pessoa update(Integer id, @Valid Pessoa pessoaObj) {
		Pessoa oldObj = findByID(id);
		
		if(findByCPF(pessoaObj.getCpf()) != null && findByCPF(pessoaObj.getCpf()).getId() != id) {
			throw new DataIntegrityException("CPF já cadastrado na base de dados");
		}
		
		oldObj.setCpf(pessoaObj.getCpf());
		oldObj.setNome(pessoaObj.getNome());
		oldObj.setTelefone(pessoaObj.getTelefone());
		return repository.save(oldObj);
	}

	public Pessoa findByCPF(String cpf) {
		Pessoa obj = repository.findByCPF(cpf);

		if (obj != null) {
			return obj;
		}
		return null;
	}

}
