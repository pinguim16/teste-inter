package com.provainter;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.provainter.mapper.UsuarioMapper;
import com.provainter.model.Digito;
import com.provainter.model.Usuario;
import com.provainter.model.dto.UsuarioDTO;
import com.provainter.service.DigitoService;
import com.provainter.service.UsuarioService;

/**
 * @author Cesar
 * @see com.provainter
 * @since 07/06/2020
 */
@SpringBootTest(classes = ProvaInterApplication.class)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DigitoServiceTest {

    @Autowired
    private DigitoService digitoService;

    @Autowired
    private UsuarioService usuarioService;

    @Test
    public void testADigitoUnico(){
        Integer resultadoTestUnico = this.digitoService.digitoUnico(4, "9875", null);
        assertEquals(8, resultadoTestUnico.intValue());

        UsuarioDTO usuario = this.usuarioService.save(UsuarioMapper.INSTANCE.usuarioToUsuarioDTO(this.getUsuario()));

        Integer resultadoTestUnic2 = this.digitoService.digitoUnico(4, "9875", usuario);
        assertEquals(8, resultadoTestUnic2.intValue());
    }

    @Test
    public void testBcocatenacaoDigitos(){
        String resultadoConcat = this.digitoService.cocatenacaoDigitos(2, "6547");
        assertEquals("65476547", resultadoConcat.toString());
    }

    @Test
    public void testCsomaDigitos(){
        Integer resultado = this.digitoService.somaDigitos("65476547");
        assertEquals(44, resultado.intValue());
    }


    public Usuario getUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNome("Jorge");
        usuario.setEmail("jorge@gmail.com");

        Digito digito = new Digito();
        digito.setEntradaK(1);
        digito.setEntradaN("1234");
        digito.setConcatP("1234");
        digito.setResultadoDigitoUnico(1);

        Set<Digito> digitos = new HashSet<>();
        digitos.add(digito);

        usuario.setDigitosUnicos(digitos);

        return usuario;
    }
}
