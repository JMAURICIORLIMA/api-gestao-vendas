package jmauriciorlima.com.github.gestao_vendas.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "descricao")
    @NotBlank(message = "Descrição")
    @Length(min = 3, max = 100, message = "Descrição")
    private String descricao;

    @Column(name = "quantidade")
    @NotNull(message = "Quantidade")
    private Integer quantidade;

    @Column(name = "preco_custo")
    @NotNull(message = "Preço Custo")
    private BigDecimal precoCusto;

    @Column(name = "preco_venda")
    @NotNull(message = "Preço Venda")
    private BigDecimal precoVenda;

    @Column(name = "observacao")
    @Length(max = 500, message = "Observação")
    private String observacao;

    @ManyToOne
    @NotNull(message = "Código Categoria")
    @JoinColumn(name = "codigo_categoria", referencedColumnName = "codigo")
    private Categoria categoria;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto produto)) return false;
        return Objects.equals(codigo, produto.codigo)
                && Objects.equals(descricao, produto.descricao)
                && Objects.equals(quantidade, produto.quantidade)
                && Objects.equals(precoCusto, produto.precoCusto)
                && Objects.equals(precoVenda, produto.precoVenda)
                && Objects.equals(observacao, produto.observacao)
                && Objects.equals(categoria, produto.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, descricao, quantidade, precoCusto, precoVenda, observacao, categoria);
    }
}
