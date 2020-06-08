package com.provainter.service.impl;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.provainter.exceptions.UsuarioNaoEncontradoException;
import com.provainter.model.MapKeyCache;
import com.provainter.model.dto.UsuarioDTO;
import com.provainter.service.CacheService;
import com.provainter.service.CriptografiaRsaService;
import com.provainter.service.UsuarioService;

/**
 * @author Cesar
 * @see com.provainter.service.impl
 * @since 07/06/2020
 */
@Service
public class CriptografiaRsaServicedImpl implements CriptografiaRsaService {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair keyPair = generator.generateKeyPair();
        return keyPair;
    }

    @Override
    public PublicKey converteStringEmChavePublica(String strPublicKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] publicBytes = Base64.decodeBase64(strPublicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        return pubKey;
    }

    @Override
    public String gerarChavePublica(Long idUsuario) throws NoSuchAlgorithmException {
        KeyPair keyPair = this.generateKeyPair();
        this.cacheService.adicionaChaveCache(idUsuario,keyPair.getPublic(), keyPair.getPrivate());
        return Base64.encodeBase64String(keyPair.getPublic().getEncoded());
    }

    @Override
    public void criptografiaComPublicaStr(String strPublicKey) throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, UsuarioNaoEncontradoException {
        PublicKey publicKey = this.converteStringEmChavePublica(strPublicKey);
        MapKeyCache mapKeyCache = this.cacheService.getPrivateKey(publicKey);
        UsuarioDTO usuario = this.usuarioService.findOne(mapKeyCache.getIdUsuario());

        if(usuario != null){
            StringBuilder dadosUsuarioConcat = new StringBuilder(usuario.getNome())
                    .append(",").append(usuario.getEmail());

            byte[] dadosCriptografados = this.criptografa(mapKeyCache.getPublicKey(), dadosUsuarioConcat.toString());
            usuario.setDadosCriptografados(dadosCriptografados);
            this.usuarioService.save(usuario);
        }else {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado");
        }
    }

    @Override
    public byte[] criptografa(PublicKey publicKey, String dadosUsuario) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(dadosUsuario.getBytes());
    }

    @Override
    public String descriptografia(PrivateKey privateKey, byte[] dadosUsuario) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(dadosUsuario));
    }
}
