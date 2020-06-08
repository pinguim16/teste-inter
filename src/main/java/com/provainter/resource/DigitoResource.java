package com.provainter.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.provainter.model.dto.UsuarioDTO;
import com.provainter.service.DigitoService;

/**
 * @author Cesar
 * @see com.provainter.resource
 * @since 05/06/2020
 */
@RestController
@RequestMapping("/digito-unico")
public class DigitoResource {

    @Autowired
    private DigitoService digitoService;

    /**
     * Entrada de dados para calculo do digito unico
     *
     * @param entradaN - string	representado um	inteiro. 1<=n<=10ˆ1000000
     * @param entradaK - número	de	vezes da concatenação
     * @param idUsuario
     */
    @GetMapping("/{entradaN}/{entradaK}")
    public ResponseEntity<?> digitoUnico(@PathVariable("entradaN") String entradaN, @PathVariable("entradaK") Integer entradaK, @RequestBody(required = false) UsuarioDTO usuario){
        if(StringUtils.isEmpty(entradaN) || entradaN.equals("null")){
            return ResponseEntity.badRequest().body("Entrada n não pode ser nula.");
        }
        if(entradaK == null){
            return ResponseEntity.badRequest().body("Entrada k não pode ser nula.");
        }
        return ResponseEntity.ok(this.digitoService.digitoUnico(entradaK,entradaN,usuario));
    }
}
