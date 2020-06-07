package com.provainter.service.impl;

import com.provainter.service.CacheService;
import com.provainter.service.CriptografiaRsaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author Cesar
 * @see com.provainter.service.impl
 * @since 07/06/2020
 */
@Service
public class CriptografiaRsaServicedImpl implements CriptografiaRsaService {

    @Autowired
    private CacheService cacheService;

    @Override
    public KeyPair genKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair keyPair = generator.generateKeyPair();
        return keyPair;
    }

    @Override
    public PublicKey converteStringEmChavePublica(String strPublicKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] publicKey2 = Base64.getDecoder().decode(strPublicKey);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePublic(new X509EncodedKeySpec(publicKey2));
    }

    @Override
    public String gerarChavePublica(Long idUsuario) throws NoSuchAlgorithmException {
        KeyPair keyPair = this.genKeyPair();
        this.cacheService.adicionaChaveCache(idUsuario,keyPair.getPublic(), keyPair.getPrivate());
        byte[] publicKey = keyPair.getPublic().getEncoded();
        return Base64.getEncoder().encodeToString(publicKey);
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
