package com.mballem.demoparkapi.service;

import com.mballem.demoparkapi.entity.Usuario;
import com.mballem.demoparkapi.exception.EntityNotFoundException;
import com.mballem.demoparkapi.exception.UsernameUniqueViolationException;
import com.mballem.demoparkapi.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
@Transactional
    public Usuario salvar(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        }
        catch (DataIntegrityViolationException ex) {
            throw new UsernameUniqueViolationException(String.format("Username do email '%s' já cadastrado",usuario.getUsername()));
        }
    }
@Transactional
    public Usuario buscarPorID(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuario id=%s não Encontrado",id))
        );
    }
@Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha){
    if(!novaSenha.equals(confirmaSenha)){
        throw new RuntimeException("Nova senha não confere com confirmação de senha");
    }
    Usuario user = buscarPorID(id);
    if(!user.getPassword().equals(senhaAtual)){
        throw new RuntimeException("Sua senha não confere");
    }
        user.setPassword(novaSenha);
        return user;
    }
    @Transactional
    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }
}
