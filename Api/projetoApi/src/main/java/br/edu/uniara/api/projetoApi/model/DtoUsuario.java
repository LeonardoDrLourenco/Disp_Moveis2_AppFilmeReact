package br.edu.uniara.api.projetoApi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class DtoUsuario {
    @Id
    @GeneratedValue
    private Long chave;

    private String nome;

    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public DtoUsuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }
}
