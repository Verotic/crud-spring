package net.aarruda.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.aarruda.crud.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    List<Aluno> findByNomeContaining(String nome);
}
