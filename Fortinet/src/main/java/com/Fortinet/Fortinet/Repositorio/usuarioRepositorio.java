package com.Fortinet.Fortinet.Repositorio;

import com.Fortinet.Fortinet.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface usuarioRepositorio extends JpaRepository<Usuario, Long> {

}
