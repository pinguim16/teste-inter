package com.provainter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.provainter.model.Digito;

/**
 * @author Cesar
 * @see com.provainter.resource
 * @since 05/06/2020
 */

@Repository
public interface DigitoRepositoy extends JpaRepository<Digito, Long> {

}
