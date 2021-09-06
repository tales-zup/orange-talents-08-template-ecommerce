package com.zup.ecommerce.produto;

import com.zup.ecommerce.caracteristica.Caracteristica;
import com.zup.ecommerce.categoria.Categoria;
import com.zup.ecommerce.opiniao.Opiniao;
import com.zup.ecommerce.pergunta.Pergunta;
import com.zup.ecommerce.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @Min(value = 0)
    private Integer quantidade;

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    @OneToMany(mappedBy = "produto")
    private List<Caracteristica> caracteristicas;

    @OneToMany(mappedBy = "produto")
    private List<Opiniao> opinioes;

    @OneToMany(mappedBy = "produto")
    private List<Pergunta> perguntas;

    @NotNull
    private LocalDateTime dataCadastro;

    public Produto() {
    }

    public Produto(String nome, BigDecimal valor, Integer quantidade, String descricao, Categoria categoria, Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
        this.dataCadastro = LocalDateTime.now();
    }

    public void associaImagens(Set<String> links) {
        Set<ImagemProduto> lista =
                links.stream().map(link -> new ImagemProduto(this,link)).collect(Collectors.toSet());

        this.imagens.addAll(lista);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public List<Opiniao> getOpinioes() {
        return opinioes;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", usuario=" + usuario +
                ", imagens=" + imagens +
                ", dataCadastro=" + dataCadastro +
                '}';
    }

    public boolean pertenceAoUsuario(Usuario usuario) {
        return Objects.equals(usuario.getId(), this.usuario.getId());
    }

    public void diminuirUmaUnidadeNoEstoque() {
        this.quantidade--;
    }
}
