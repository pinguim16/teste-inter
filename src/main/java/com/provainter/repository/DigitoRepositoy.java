package com.provainter.repository;

import com.provainter.model.Digito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Cesar
 * @see com.provainter.resource
 * @since 05/06/2020
 */

@Repository
public interface DigitoRepositoy extends JpaRepository<Digito, Long> {
}
