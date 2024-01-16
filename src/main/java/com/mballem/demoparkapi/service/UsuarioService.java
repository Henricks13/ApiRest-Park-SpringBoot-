package com.mballem.demoparkapi.service;

import com.mballem.demoparkapi.entity.Usuario;
import com.mballem.demoparkapi.exception.EntityNotFoundException;
import com.mballem.demoparkapi.exception.PasswordInvalidException;
import com.mballem.demoparkapi.exception.UsernameUniqueViolationException;
import com.mballem.demoparkapi.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
@Transactional
    public Usuario salvar(Usuario usuario) {
        try {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            return usuarioRepository.save(usuario);
        }
        catch (DataIntegrityViolationException ex) {
            throw new UsernameUniqueViolationException(String.format("Username do email '%s' já cadastrado",usuario.getUsername()));
        }
    }
@Transactional
    public Usuario buscarPorID(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuario id=%s não encontrado",id))
        );
    }
@Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha){
    if(!novaSenha.equals(confirmaSenha)){
        throw new PasswordInvalidException("Nova senha não confere com confirmação de senha");
    }
    Usuario user = buscarPorID(id);
    if(!passwordEncoder.matches(senhaAtual,user.getPassword())){
        throw new PasswordInvalidException("Sua senha não confere");
    }
        user.setPassword(passwordEncoder.encode(novaSenha));
        return user;
    }
    @Transactional
    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario buscaPorUsername(String username) {
    return usuarioRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuario com '%s' não encontrado",username))
        );
    }
    @Transactional
    public Usuario.Role buscarRolePorUsername(String username) {

    return usuarioRepository.findRoleByUsername(username);


    }
}
