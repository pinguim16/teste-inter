package com.provainter.resource;

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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.provainter.model.dto.UsuarioDTO;
import com.provainter.service.DigitoService;

/**
 * @author Cesar
 * @see com.provainter.resource
 * @since 05/06/2020
 */
@RestController
@RequestMapping("/digito-unico")
@Api(tags = "Endpoints relacionados ao cálculo de digitoUnico")
public class DigitoResource {

    @Autowired
    private DigitoService digitoService;

    private static final String ENTITY_NAME = "digito";

    private final Logger log = LoggerFactory.getLogger(DigitoResource.class);


    /**
     * Entrada de dados para calculo do digito unico
     *
     * @param entradaN - string	representado um	inteiro. 1<=n<=10ˆ1000000
     * @param entradaK - número	de	vezes da concatenação
     * @param idUsuario
     */
    @ApiOperation(value = "Endpoint para realizar cálculo do digitoUnico")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retorna resultado do cálculo de digito único."),
            @ApiResponse(code = 400, message = "Retorno em caso de valores nulos enviados.")})
    @GetMapping("/{entradaN}/{entradaK}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> digitoUnico(@PathVariable("entradaN") String entradaN, @PathVariable("entradaK") Integer entradaK, @RequestBody(required = false) UsuarioDTO usuario){
        log.debug("REST request para cálculo de digitoUnico : {}, {}, {}", entradaN, entradaK, usuario);
        if(StringUtils.isEmpty(entradaN) || entradaN.equals("null")){
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.criacaoAlertaFalha(ENTITY_NAME, "entradaN", "EntradaN enviada em valor null.")).body(null);
        }
        if(entradaK == null){
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.criacaoAlertaFalha(ENTITY_NAME, "entradaK", "EntradaK enviada em valor null.")).body(null);
        }
        return ResponseEntity.ok(this.digitoService.digitoUnico(entradaK,entradaN,usuario));
    }
}
