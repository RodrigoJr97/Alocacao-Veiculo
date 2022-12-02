package com.alugacarro.alugacarro.domain.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Campo Obrigatório - Marca")
    private String marca;

    @NotEmpty(message = "Campo Obrigatório - Modelo")
    private String modelo;

    @NotEmpty(message = "Obrigatório - Tipo")
    private String tipo;

    @NotEmpty(message = "Obrigatório - Cor")
    private String cor;

    @NotNull(message = "Obrigatório - Numero de Portas")
    private int numeroDePortas;

    @NotEmpty(message = "brigatório - Tipo Combustivel")
    private String tipoCombustivel;

    @NotEmpty(message = "Obrigatório - Ano Fabricação")
    private String anoFabricacao;

    @Column(name = "valor_diaria")
    @NotNull(message = "Obrigatório - Valor Diária")
    private BigDecimal valorDiaria;

    private boolean disponivel = true;

    // @NotEmpty
    private String img;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Carro carro = (Carro) o;
        return id != null && Objects.equals(id, carro.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
