package com.provainter.resource;

import com.provainter.model.dto.DigitoDTO;
import com.provainter.model.dto.UsuarioDTO;
import com.provainter.service.UsuarioService;
import com.provainter.util.HeaderUtil;
import com.provainter.util.PaginationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

/**
 * @author Cesar
 * @see com.provainter.resource
 * @since 05/06/2020
 */
@RestController
@RequestMapping("/usuario")
@Api(tags = "Endpoints relacionados ao crud de usuário")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    private final Logger log = LoggerFactory.getLogger(UsuarioResource.class);

    private static final String ENTITY_NAME = "usuario";

    /**
     *
     * POST /usuario : Criação de novo usuario
     *
     * @param usuarioDTO para criação
     * @return ResponseEntity com status 201 (created) e com body com novo usuarioDTO, ou com status 400 (bad request) se usuário já possuir um id
     * @throws URISyntaxException se o URI possuir sintaxe incorreta
     */
    @ApiOperation(value = "Endpoint para cadastro de usuários")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Retorna usuário cadastrado"),
                          @ApiResponse(code = 400, message = "Novo usuário não possui id.")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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
    @ApiOperation(value = "Endpoint para update de usuários")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retorna usuário cadastrado"),
            @ApiResponse(code = 400, message = "Retorno em caso de ser enviado nome ou e-mail nulos ou em branco.")})
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UsuarioDTO> updateUsuario(@RequestBody UsuarioDTO usuarioDTO) throws URISyntaxException {
        log.debug("REST request para update do Usuário : {}", usuarioDTO);
        if(usuarioDTO.getId() == null){
            return this.criacaoUsuario(usuarioDTO);
        }
        if(usuarioDTO.getNome().isEmpty() || usuarioDTO.getEmail().isEmpty()){
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.criacaoAlertaFalha(ENTITY_NAME, "nomeOuEmail", "Usuário deverá possuir nome e e-mail.")).body(null);
        }
        UsuarioDTO usuarioSalvo = this.usuarioService.save(usuarioDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.criacaoAlertaParaEntidadeAtualizada(ENTITY_NAME, usuarioSalvo.getId().toString()))
                .body(usuarioSalvo);
    }

    /**
     * GET /usuario : get todos usuarios
     *
     * @param pageable informações de paginação
     * @return ResponseEntity com status 200 (ok) e
     * lista de usuarios no body
     */
    @ApiOperation(value = "Endpoint que retorna todos os usuários cadastrados")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retorna listagem de usuários cadastrados.")})
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
     * @return return com status 200 (ok) com usuarioDTO encontrado
     * ou 204 (no content) para usuario não encotrado
     */
    @ApiOperation(value = "Endpoint para buscar usuário cadastrado")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retorna usuário cadastrado."),
            @ApiResponse(code = 400, message = "Retorno em casos de usuários não encontrados.")})
    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable("idUsuario") Long idUsuario){
        log.debug("REST request para buscar Usuario : {}", idUsuario);
        try {
            UsuarioDTO usuarioDTO = this.usuarioService.findOne(idUsuario);
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
     * ou 400 (bad request) para usuario não encontrado
     */
    @ApiOperation(value = "Endpoint para deletar usuário cadastrado")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Deleta usuário cadastrado."),
            @ApiResponse(code = 400, message = "Retorno em casos de usuários não encontrados.")})
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<?> deleteUsuario(@PathVariable("idUsuario") Long idUsuario){
        log.debug("REST request para deletar usuario : {}", idUsuario);
        try {
            this.usuarioService.delete(idUsuario);
            return ResponseEntity.ok().headers(HeaderUtil.criacaoAlertaParaEntidadeDeletada(ENTITY_NAME, idUsuario.toString())).build();
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.badRequest().body("Usuário não encontrado para deletar.");
        }
    }

    /**
     * GET /listar-calculos/idUsuario - Buscar todos os calculos realizados por um usuario
     *
     * @param idUsuario para buscar os resultados
     * @return ResponseEntity com status 200 (OK) com a lista dos calculos realizados
     * ou 400 (bad request) para usuario nulo
     */
    @ApiOperation(value = "Endpoint para retornar todos os calculos realizados por um usuário.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retorna os calculos por usuários."),
            @ApiResponse(code = 400, message = "Retorno em caso de usuários não encontrados.")})
    @GetMapping("/listar-calculos/{idUsuario}")
    public ResponseEntity<Set<DigitoDTO>> buscarCalculosPorUsuario(@PathVariable("idUsuario") Long idUsuario){
        if(idUsuario == null) {
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.criacaoAlertaFalha(ENTITY_NAME, "idUsuárioInvalido", "Não é possível pesquisar cálculos sem id do usuário.")).body(null);
        }
        return ResponseEntity.ok(this.usuarioService.findOne(idUsuario).getDigitosUnicos());
    }
}
