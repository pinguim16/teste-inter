package com.provainter.service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.provainter.exceptions.UsuarioNaoEncontradoException;

import java.security.*;
import java.security.spec.InvalidKeySpecException;

/**
 * @author Cesar
 * @see com.provainter.service
 * @since 07/06/2020
 */
public interface CriptografiaRsaService {

    byte[] criptografa(PublicKey publicKey, String dadosUsuario) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException;

    String descriptografia(PrivateKey privateKey, byte[] dadosUsuario) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException;

    KeyPair generateKeyPair() throws NoSuchAlgorithmException;

    PublicKey converteStringEmChavePublica(String strPublicKey) throws InvalidKeySpecException, NoSuchAlgorithmException;

    String gerarChavePublica(Long idUsuario) throws NoSuchAlgorithmException;

    void criptografiaComPublicaStr(String strPublicKey) throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, UsuarioNaoEncontradoException;

}
