package com.provainter.resource;

import com.provainter.model.dto.UsuarioDTO;
import com.provainter.service.UsuarioService;
import com.provainter.util.HeaderUtil;
import com.provainter.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author Cesar
 * @see com.provainter.resource
 * @since 05/06/2020
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

    private final Logger log = LoggerFactory.getLogger(UsuarioResource.class);

    private static final String ENTITY_NAME = "usuario";

    @Autowired
    private UsuarioService usuarioService;

    /**
     *
     * POST /usuario : Criação de novo usuario
     *
     * @param usuarioDTO para criação
     * @return ResponseEntity com status 201 (created) e com body com novo usuarioDTO, ou com status 400 (bad request) se usuário já possuir um id
     * @throws URISyntaxException se o URI possuir sintaxe incorreta
     */
    @PostMapping
    public ResponseEntity<UsuarioDTO> criacaoUsuario(@RequestBody UsuarioDTO usuarioDTO) throws URISyntaxException {
        log.debug("REST request para cadastrar Usuário : {}", usuarioDTO);
        if(usuarioDTO.getId() != null){
            return ResponseEntity.badRequest().headers(HeaderUtil.criacaoAlertaFalha(ENTITY_NAME, "idExistente", "Novo usuário não possui id.")).body(null);
        }
        UsuarioDTO usuarioDTOSalvo = this.usuarioService.save(usuarioDTO);
        return ResponseEntity.created(new URI("/api/usuario/" + usuarioDTOSalvo.getId()))
                .headers(HeaderUtil.criacaoAlertaParaEntidadeCriada(ENTITY_NAME, usuarioDTOSalvo.getId().toString()))
                .body(usuarioDTOSalvo);
    }

    /**
     * PUT /usuario : Update de usuário existente
     *
     * @param usuarioDTO para update
     * @return ResponseEntity com status 200 (ok) e com usuarioDTO no body
     * ou com status 400 (bad request) se o usuarioDTO não for válido,
     * ou com status 500 (internal server error) is usuariodto não for atualizado
     * @throws URISyntaxException se o URI possuir sintaxe incorreta
     */
    @PutMapping
    public ResponseEntity<UsuarioDTO> updateUsuario(@RequestBody UsuarioDTO usuarioDTO) throws URISyntaxException {
        log.debug("REST request para update do Usuário : {}", usuarioDTO);
        if(usuarioDTO.getId() == null){
            return this.criacaoUsuario(usuarioDTO);
        }
        UsuarioDTO usuarioSalvo = this.usuarioService.save(usuarioDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.criacaoAlertaParaEntidadeDeletada(ENTITY_NAME, usuarioSalvo.getId().toString()))
                .body(usuarioSalvo);
    }

    /**
     * GET /usuario : get todos usuarios
     *
     * @param pageable informações de paginação
     * @return ResponseEntity com status 200 (ok) e lista de usuarios no body
     */
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuario(@RequestParam("size") Integer size, @RequestParam("page") Integer page){
        log.debug("REST request para buscar uma pagina de Usuario");
        Page<UsuarioDTO> usuarioDTOS = this.usuarioService.findAll(PageRequest.of(page,size));
        HttpHeaders headers = PaginationUtil.generacaoPaginacaoHttpHeaders(usuarioDTOS, "/api/arquivos");
        return new ResponseEntity<>(usuarioDTOS.getContent(), headers, HttpStatus.OK);
    }

    /**
     *  GET /usuario/id : para buscar usuario por id
     *
     * @param id para recuperar usuario
     * @return return com status 200 (ok) com usuarioDTO encontrado ou 204 (no content) para usuario não encotrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable Long id){
        log.debug("REST request para buscar Usuario : {}", id);
        try {
            UsuarioDTO usuarioDTO = this.usuarioService.findOne(id);
            if (usuarioDTO != null) {
                return ResponseEntity.ok().body(usuarioDTO);
            } else {
                return ResponseEntity.noContent().build();
            }
        }catch (EntityNotFoundException e){
            return ResponseEntity.noContent().build();
        }
    }

    /**
     * DELETE /usuario/id : para deletar usuario por id
     *
     * @param id para deletar usuario
     * @return ResponseEntity com status 200 (OK)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id){
        log.debug("REST request para deletar usuario : {}", id);
        this.usuarioService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.criacaoAlertaParaEntidadeDeletada(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/listar-calculos/{idUsuario}")
    public ResponseEntity<?> buscarCalculosPorUsuario(@PathVariable("idUsuario") Long idUsuario){
        if(idUsuario == null){
            return ResponseEntity.badRequest().body("Não é possível pesquisar cálculos sem id do usuário.");
        }
        return ResponseEntity.ok(this.usuarioService.findOne(idUsuario).getDigitosUnicos());
    }
}
