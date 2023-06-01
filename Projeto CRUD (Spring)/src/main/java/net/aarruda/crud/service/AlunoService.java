package net.aarruda.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import net.aarruda.crud.entity.Aluno;
import net.aarruda.crud.repository.AlunoRepository;

@Service
@Transactional
public class AlunoService {

    @Autowired
    private AlunoRepository repo;


    public List<Aluno> listAll() {
        return repo.findAll();
    }

    public List<Aluno> search(String keyword) {
        if (keyword != null) {
            return repo.findByNomeContaining(keyword);
        }
        return repo.findAll();
    }

    public void save(Aluno aluno) {
        repo.save(aluno);
    }

    public Aluno get(Integer id) {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
