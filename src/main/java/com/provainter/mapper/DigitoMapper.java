package com.provainter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.provainter.model.Digito;
import com.provainter.model.dto.DigitoDTO;

/**
 * @author Cesar
 * @see com.provainter.mapper
 * @since 07/06/2020
 */
@Mapper
public interface DigitoMapper {

    DigitoMapper INSTANCE = Mappers.getMapper(DigitoMapper.class);

    DigitoDTO digitoToDigitoDTO(Digito digito);

    Digito digitoDTOToDigito(DigitoDTO digitoDTO);

}
