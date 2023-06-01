package net.aarruda.crud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "aluno")

public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = true)
    private String nacionalidade;
    @Column(nullable = true)
    private String foto;

    @Transient
	public String getfotosPath() {
		if (foto == null || id == null) return null;
		
		return "/alunos-fotos/" + id + "/" + foto;
	}

}
