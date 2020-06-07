package com.provainter.resource;

import com.provainter.service.CriptografiaRsaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.text.html.parser.Entity;
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

    @GetMapping
    public void teste() throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, NoSuchProviderException, InvalidKeySpecException {
        KeyPair keyPair = this.criptografiaRsaService.genKeyPair();

        byte[] criptografado = this.criptografiaRsaService.criptografa(keyPair.getPublic(), "teste");
        String descriptografado = this.criptografiaRsaService.descriptografia(keyPair.getPrivate(), criptografado);


        byte[] publicKey = keyPair.getPublic().getEncoded();
        String str_pubKey = Base64.getEncoder().encodeToString(publicKey);
        System.out.println(str_pubKey);

        byte[] publicKey2 = Base64.getDecoder().decode(str_pubKey);
        KeyFactory factory = KeyFactory.getInstance("RSA");

        PublicKey publicKeyRev = factory.generatePublic(new X509EncodedKeySpec(publicKey2));

        byte[] bytesPrivatekey = keyPair.getPrivate().getEncoded();
        String str_priKey = Base64.getEncoder().encodeToString(publicKey);
        System.out.println(str_priKey);
        PrivateKey privateKey = factory.generatePrivate(new X509EncodedKeySpec(bytesPrivatekey));

        criptografado = this.criptografiaRsaService.criptografa(publicKeyRev, "teste");
        descriptografado = this.criptografiaRsaService.descriptografia(privateKey, criptografado);

        System.out.println(criptografado.toString());
        System.out.println(descriptografado);

    }

    @GetMapping("/gerar-chave-publica/{idUsuario}")
    public ResponseEntity gerarChavePublic(@PathVariable("idUsuario") Long idUsuario) throws NoSuchAlgorithmException {
        return ResponseEntity.ok().body(this.criptografiaRsaService.gerarChavePublica(idUsuario));
    }
}
