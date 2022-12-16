package com.alugacarro.alugacarro.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class EnderecoCliente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10)
    @NotEmpty(message = "CEP Obrigatório")
    private String cep;

    @Column(length = 40)
    @NotEmpty(message = "Campo Obrigatório: RUA")
    private String rua;

    @NotNull(message = "Campo Obrigatório: NUMERO LOGRADOURO")
    private Integer numeroLogradouro;

    @Column(length = 40)
    @NotEmpty(message = "Campo Obrigatório: BAIRRO")
    private String bairro;

    @Column(length = 40)
    @NotEmpty(message = "Campo Obrigatório: CIDADE")
    private String cidade;

    @Column(length = 40)
    @NotEmpty(message = "Campo Obrigatório: ESTADO")
    private String estado;

    private String complemento;


    @OneToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "id")
    private Cliente cliente;

}
