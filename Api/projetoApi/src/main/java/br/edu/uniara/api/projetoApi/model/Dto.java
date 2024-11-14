package br.edu.uniara.api.projetoApi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Dto {
    @Id
    @GeneratedValue
    private Long chave;

    private String nome;

    private String tipo;

    private String preco;

    @Column(length = 10000)
    private String imagem;

    public Dto(String nome, String tipo, String preco, String imagem) {
        this.nome = nome;
        this.tipo = tipo;
        this.preco = preco;
        this.imagem = imagem;
    }
}
