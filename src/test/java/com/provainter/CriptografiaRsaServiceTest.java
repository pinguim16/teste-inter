package com.provainter;

import com.provainter.exceptions.UsuarioNaoEncontradoException;
import com.provainter.model.MapKeyCache;
import com.provainter.model.dto.DigitoDTO;
import com.provainter.model.dto.UsuarioDTO;
import com.provainter.repository.UsuarioRepository;
import com.provainter.service.CacheService;
import com.provainter.service.CriptografiaRsaService;
import com.provainter.service.UsuarioService;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * @author Cesar
 * @see com.provainter
 * @since 07/06/2020
 */
@SpringBootTest(classes = ProvaInterApplication.class)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CriptografiaRsaServiceTest {

    @Autowired
    private CriptografiaRsaService criptografiaRsaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CacheService cacheService;

    @Test
    public void testA() throws NoSuchAlgorithmException, InvalidKeySpecException, UsuarioNaoEncontradoException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException {
        KeyPair keyPair = this.criptografiaRsaService.generateKeyPair();
        assertNotNull(keyPair);

        UsuarioDTO usuarioDTO = this.usuarioService.save(this.getUsuario());
        String chavePublica = this.criptografiaRsaService.gerarChavePublica(usuarioDTO.getId());
        assertNotNull(chavePublica);

        this.criptografiaRsaService.criptografiaComPublicaStr(chavePublica);
        PublicKey publicKey = this.criptografiaRsaService.converteStringEmChavePublica(chavePublica);
        MapKeyCache mapKeyCache = this.cacheService.getPrivateKey(publicKey);

        usuarioDTO = this.usuarioService.findOne(usuarioDTO.getId());
        String descriptografia = this.criptografiaRsaService.descriptografia(mapKeyCache.getPrivateKey(), usuarioDTO.getDadosCriptografados());
        String dadosUsuarios = "Jorge,jorge@gmail.com";
        assertEquals(dadosUsuarios, descriptografia);
    }

    public UsuarioDTO getUsuario(){
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNome("Jorge");
        usuario.setEmail("jorge@gmail.com");

        DigitoDTO digito = new DigitoDTO();
        digito.setEntradaK(1);
        digito.setEntradaN("1234");
        digito.setConcatP("1234");
        digito.setResultadoDigitoUnico(1);

        Set<DigitoDTO> digitos = new HashSet<>();
        digitos.add(digito);

        usuario.setDigitosUnicos(digitos);
        return usuario;
    }
}
