package com.mballem.demoparkapi.service;

import com.mballem.demoparkapi.entity.Usuario;
import com.mballem.demoparkapi.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
@Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
@Transactional
    public Usuario buscarPorID(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuario não Encontrado")
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
