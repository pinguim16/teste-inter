package com.provainter.resource;

import com.provainter.exceptions.UsuarioNaoEncontradoException;
import com.provainter.service.CriptografiaRsaService;
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
public class CriptografiaResource {

    @Autowired
    private CriptografiaRsaService criptografiaRsaService;

    @GetMapping("/gerar-chave-publica/{idUsuario}")
    public ResponseEntity<?> gerarChavePublic(@PathVariable("idUsuario") Long idUsuario) throws NoSuchAlgorithmException {
        if(idUsuario != null) {
            return ResponseEntity.ok().body(this.criptografiaRsaService.gerarChavePublica(idUsuario));
        }
        return ResponseEntity.badRequest().body("Não é possui gerar chave sem usuário.");
    }

    @PostMapping
    public ResponseEntity<?> criptografiaDadosUsuario(@RequestBody String chavePublica) {
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
