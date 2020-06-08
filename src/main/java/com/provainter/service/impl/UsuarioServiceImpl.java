package com.provainter.service.impl;

import com.provainter.mapper.UsuarioMapper;
import com.provainter.model.Usuario;
import com.provainter.model.dto.UsuarioDTO;
import com.provainter.repository.UsuarioRepository;
import com.provainter.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author Cesar
 * @see com.provainter.service.impl
 * @since 06/06/2020
 */
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Gravar Usuário
     *
     * @param usuarioDTO para persistir
     * @return entidade persistida
     */
    @Override
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        log.debug("Request para salvar usuario : {}", usuarioDTO);
        Usuario usuario = UsuarioMapper.INSTANCE.usuarioDTOToUsuario(usuarioDTO);
        usuario = this.usuarioRepository.save(usuario);
        return UsuarioMapper.INSTANCE.usuarioToUsuarioDTO(usuario);
    }

    /**
     * Buscar todos os usuarios
     *
     * @param pageable informações da página a ser pesquisada
     * @return lista dos usuários
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UsuarioDTO> findAll(Pageable pageable) {
        log.debug("Request para buscar usuários de acordo com pageable");
        Page<Usuario> usuarioPage = this.usuarioRepository.findAll(pageable);
        Page<UsuarioDTO> pageUsuarioDTO = usuarioPage.map(usuario -> UsuarioMapper.INSTANCE.usuarioToUsuarioDTO(usuario));
        return pageUsuarioDTO;
    }

    /**
     * Buscar usuário por id
     *
     * @param id da entitade a ser pesquisada
     * @return entidade a ser pesquisada
     */
    @Override
    public UsuarioDTO findOne(Long id) {
        log.debug("Request buscar usuario : {}", id);
        Usuario usuario = this.usuarioRepository.getOne(id);
        return UsuarioMapper.INSTANCE.usuarioToUsuarioDTO(usuario);
    }

    /**
     * Deleta usuário pelo id
     *
     * @param id da entidade a ser deletada
     */
    @Override
    public void delete(Long id) {
        log.debug("Request para deletar usuario : {}", id);
        this.usuarioRepository.deleteById(id);
    }

    /**
     * Deleta todos os usuário
     */
    @Override
    public void deleteAllUsuarios() {
       this.usuarioRepository.deleteAll();
    }
}
