package com.mballem.demoparkapi.service;

import com.mballem.demoparkapi.entity.Cliente;
import com.mballem.demoparkapi.exception.CpfUniqueViolationException;
import com.mballem.demoparkapi.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar (Cliente cliente){
        try {

            return clienteRepository.save(cliente);

        }
        catch (DataIntegrityViolationException ex){
            throw new CpfUniqueViolationException(
                    String.format("CPF '%s' não pode ser cadastrado, já existe no sistema",cliente.getCpf()));
        }
    }

}
