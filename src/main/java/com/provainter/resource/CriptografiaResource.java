package com.provainter.resource;

import com.provainter.exceptions.UsuarioNaoEncontradoException;
import com.provainter.service.CriptografiaRsaService;
import com.provainter.util.HeaderUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author Cesar
 * @see com.provainter.resource
 * @since 07/06/2020
 */
@RestController
@RequestMapping("/criptografia")
@Api(tags = "Endpoints relacionados a criptografia.")
public class CriptografiaResource {

    private static final String ENTITY_NAME = "criptografia";

    private final Logger log = LoggerFactory.getLogger(CriptografiaResource.class);

    @Autowired
    private CriptografiaRsaService criptografiaRsaService;

    /**
     * GET /gerar-chave-publica/idUsuario : Geração de chave pública para usuário
     *
     * @param idUsuario
     * @return ResponseEntity com status 200 (ok) e com body chave pública, ou com status 400 (bad request) se id do usuário não for enviado
     * @throws NoSuchAlgorithmException erro ao tentar gerar as chaves para o usuário
     */
    @ApiOperation(value = "Endpoint para geração de chave pública para usuário")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retorna chave pública gerada."),
            @ApiResponse(code = 400, message = "Se id do usuário não for enviado.")})
    @GetMapping("/gerar-chave-publica/{idUsuario}")
    public ResponseEntity<String> gerarChavePublic(@PathVariable("idUsuario") Long idUsuario) throws NoSuchAlgorithmException {
        if(idUsuario != null) {
            return ResponseEntity.ok().body(this.criptografiaRsaService.gerarChavePublica(idUsuario));
        }
        return ResponseEntity.badRequest().headers(HeaderUtil.criacaoAlertaFalha(ENTITY_NAME, "idInexistente", "Id do Usuário não pode ser nulo.")).body(null);
    }

    /**
     * POST /criptografia : para gerar a criptografia dos dados usuário
     * @param chavePublica para recuperação e geração da criptografia dos dados do usuário
     * @return string para confirmar que dados foram criptografados
     */
    @ApiOperation(value = "Endpoint para geração de chave pública para usuário")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retorna confirmação de criptografia gerada."),
            @ApiResponse(code = 400, message = "Caso usuário não seja encontrado."),
            @ApiResponse(code = 500, message = "Caso aconteça algo erro na geração da criptografia.")})
    @PostMapping
    public ResponseEntity<String> criptografiaDadosUsuario(@RequestBody String chavePublica) {
        try {
            this.criptografiaRsaService.criptografiaComPublicaStr(chavePublica);
            return ResponseEntity.ok().body("Dados Criptografados com sucesso");
        }catch (UsuarioNaoEncontradoException usuEx){
            return ResponseEntity.badRequest().body(usuEx.getMessage());
        }catch (BadPaddingException | NoSuchAlgorithmException | IllegalBlockSizeException | NoSuchPaddingException | InvalidKeyException | InvalidKeySpecException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao tentar realizar a criptografia dos dados do usuário");
        }
    }
}
