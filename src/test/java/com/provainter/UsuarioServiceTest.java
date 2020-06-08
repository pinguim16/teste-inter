package com.provainter;

import com.provainter.mapper.UsuarioMapper;
import com.provainter.model.Digito;
import com.provainter.model.Usuario;
import com.provainter.model.dto.UsuarioDTO;
import com.provainter.service.UsuarioService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
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
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @Test
    @Order(1)
    public void testASalvarUsuario(){
        UsuarioDTO usuarioDTO = UsuarioMapper.INSTANCE.usuarioToUsuarioDTO(this.getUsuario());
        usuarioDTO = this.usuarioService.save(usuarioDTO);

        assertNotNull(usuarioDTO);
        assertEquals(1L,usuarioDTO.getId().longValue());
        assertEquals("Jorge", usuarioDTO.getNome());
        assertEquals("jorge@gmail.com", usuarioDTO.getEmail());

        assertEquals(1, usuarioDTO.getDigitosUnicos().size());
    }

    @Test
    @Order(2)
    public void testBFindAllUsuario(){
        Page<UsuarioDTO> usuarioDTOPage = this.usuarioService.findAll(PageRequest.of(0, 1));
        assertEquals(1L,usuarioDTOPage.getTotalElements());
        assertEquals(1L,usuarioDTOPage.getContent().get(0).getId().longValue());
    }

    @Test
    @Order(3)
    public void testCFindOneUsuario(){
        UsuarioDTO usuarioDTO = this.usuarioService.findOne(1L);
        assertNotNull(usuarioDTO);
        assertEquals(1L,usuarioDTO.getId().longValue());
        assertEquals("Jorge", usuarioDTO.getNome());
        assertEquals("jorge@gmail.com", usuarioDTO.getEmail());
    }

    @Order(4)
    @Test(expected = EntityNotFoundException.class)
    public void testDDeleteUser(){
        this.usuarioService.delete(1L);
        this.usuarioService.findOne(1L);
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

