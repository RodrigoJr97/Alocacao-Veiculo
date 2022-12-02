package com.alugacarro.alugacarro.domain.entity;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    @NotEmpty(message = "Obrigatório - Nome")
    private String nome;

    @Column(length = 11, unique = true)
    @NotEmpty(message = "Obrigatório - CPF")
   // @CPF
    private String cpf;

    @Column(name = "numero_telefone",length = 15, unique = true)
    @NotEmpty@NotEmpty(message = "Obrigatório - Número Telefone")
    private String numeroTelefone;

    @Column(length = 50, unique = true)
    @NotEmpty@NotEmpty(message = "Obrigatório - Email")
    // @Email
    private String email;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idEndereco", referencedColumnName = "id")
    @NotNull(message = "Endereço Obrigatório")
    @Valid
    private EnderecoCliente endereco;

    private boolean disponivelParaContrato = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(nome, cliente.nome) && Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cpf);
    }
}
