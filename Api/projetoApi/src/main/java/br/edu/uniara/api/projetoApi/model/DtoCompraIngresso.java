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
public class DtoCompraIngresso {
    @Id
    @GeneratedValue
    private Long chave;

    private String filme;

    private String nomeComprador;

    private String Horario;

    private String Dia;

    private String Preco;

    public DtoCompraIngresso(String nomeComprador, String Horario, String Dia, String Preco) {
        this.nomeComprador = nomeComprador;
        this.Horario = Horario;
        this.Dia = Dia;
        this.Preco = Preco;
    }
}
