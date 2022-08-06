package com.reinaldo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.reinaldo.domain.Pessoa;
import com.reinaldo.repositories.PessoaRepository;

@SpringBootApplication
public class CrudPessoaApplication implements CommandLineRunner{
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public static void main(String[] args) {
		SpringApplication.run(CrudPessoaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Pessoa p1 = new Pessoa(null, "Reinaldo", "02958859201", "(62) 99573-0262");
		Pessoa p2 = new Pessoa(null, "Bruna", "95201669385", "(62) 99622-0522");
		
		pessoaRepository.saveAll(Arrays.asList(p1,p2));
		
	}

}
