package jmauriciorlima.com.github.gestao_vendas.dto.produto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import jmauriciorlima.com.github.gestao_vendas.dto.categoria.CategoriaResponseDTO;
import jmauriciorlima.com.github.gestao_vendas.entidades.Produto;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public class ProdutoResponseDTO {

    private Long codigo;

    @Length(min = 3, max = 100, message = "Descrição")
    @Schema(name = "nome", description = "Descrição")
    private String descricao;

    @NotNull(message = "Quantidade")
    @Schema(name = "quantidade", description = "Quantidade")
    private Integer quantidade;

    @NotNull(message = "Preço Custo")
    @Schema(name = "precoCusto", description = "Preço custo")
    private BigDecimal precoCusto;

    @NotNull(message = "Preço Venda")
    @Schema(name = "precoVenda", description = "Preço Venda")
    private BigDecimal precoVenda;

    @Length(max = 500, message = "Observação")
    @Schema(name = "observacao", description = "Observação")
    private String observacao;

    @NotNull(message = "Código Categoria")
    @Schema(name = "codigoCategoria", description = "Código categoria")
    private CategoriaResponseDTO categoria;

    public ProdutoResponseDTO(Long codigo,
                              String descricao,
                              Integer quantidade,
                              BigDecimal precoCusto,
                              BigDecimal precoVenda,
                              String observacao,
                              CategoriaResponseDTO categoria) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.observacao = observacao;
        this.categoria = categoria;
    }

    public static ProdutoResponseDTO converterParaProdutoDTO(Produto produto) {
        return new ProdutoResponseDTO(produto.getCodigo(),
                produto.getDescricao(), produto.getQuantidade(),
                produto.getPrecoCusto(), produto.getPrecoVenda(),
                produto.getObservacao(), CategoriaResponseDTO.converterParaCategoriaDTO(produto.getCategoria()));
    }

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

    public CategoriaResponseDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaResponseDTO categoria) {
        this.categoria = categoria;
    }
}
