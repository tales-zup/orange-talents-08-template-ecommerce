package com.zup.ecommerce.produto;

import com.zup.ecommerce.categoria.Categoria;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    private BigDecimal valor;

    @NotNull
    @Min(value = 0)
    private Integer quantidade;

    @NotBlank
    private String descricao;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @NotNull
    private LocalDateTime dataCadastro;

}
