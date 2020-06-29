package com.mercado.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mercado.model.Pessoa;
import com.mercado.repository.PessoaRepository;

@Service
public class DetailServicesImpl implements UserDetailsService{
	
	@Autowired
	private PessoaRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Pessoa usuario = userRepo.findByLogin(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não foi encontrador!!!");
		}

		return new User(usuario.getLogin(),
				usuario.getPassword(),
				usuario.getAuthorities());
	}
}
