package com.provainter.service;

import com.provainter.model.dto.UsuarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Cesar
 * @see com.provainter.resource
 * @since 05/06/2020
 */

public interface UsuarioService {

    /**
     * Gravar Usuário
     *
     * @param usuarioDTO para persistir
     * @return entidade persistida
     */
    UsuarioDTO save(UsuarioDTO usuarioDTO);

    /**
     * Buscar todos os usuarios
     *
     * @param pageable informações da página a ser pesquisada
     * @return  lista dos usuários
     */
    Page<UsuarioDTO> findAll(Pageable pageable);

    /**
     * Buscar usuário por id
     *
     * @param id da entitade a ser pesquisada
     * @return entidade a ser pesquisada
     */
    UsuarioDTO findOne(Long id);

    /**
     * Deleta usuário pelo id
     *
     * @param id da entidade a ser deletada
     */
    void delete(Long id);

}