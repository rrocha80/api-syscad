package br.com.softplan.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softplan.api.domain.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
