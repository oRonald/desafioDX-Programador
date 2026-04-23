package br.com.duxusdesafio.repository;

import br.com.duxusdesafio.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface TimeRepository extends JpaRepository<Time, Long> {

    boolean existsByNomeDoClubeAndData(String nomeDoClube, LocalDate data);
}
