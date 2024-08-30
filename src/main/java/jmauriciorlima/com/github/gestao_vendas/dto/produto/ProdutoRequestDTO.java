package jmauriciorlima.com.github.gestao_vendas.dto.produto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jmauriciorlima.com.github.gestao_vendas.entidades.Categoria;
import jmauriciorlima.com.github.gestao_vendas.entidades.Produto;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public class ProdutoRequestDTO {

    @NotBlank(message = "Descrição")
    @Length(min = 3, max = 100, message = "Descrição")
    @Schema(name = "descricao", description = "Descrição")
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

    public Produto converterParaEntidade(Long codigoCategoria) {
        return new Produto(descricao,
                quantidade,
                precoCusto,
                precoVenda,
                observacao,
                new Categoria(codigoCategoria));
    }

    public Produto converterParaEntidade(Long codigoCategoria, Long codigoProduto) {
        return new Produto(codigoProduto,
                descricao,
                quantidade,
                precoCusto,
                precoVenda,
                observacao,
                new Categoria(codigoCategoria));
    }

    public @NotBlank(message = "Descrição") @Length(min = 3, max = 100, message = "Descrição") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank(message = "Descrição") @Length(min = 3, max = 100, message = "Descrição") String descricao) {
        this.descricao = descricao;
    }

    public @NotNull(message = "Quantidade") Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(@NotNull(message = "Quantidade") Integer quantidade) {
        this.quantidade = quantidade;
    }

    public @NotNull(message = "Preço Custo") BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(@NotNull(message = "Preço Custo") BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }

    public @NotNull(message = "Preço Venda") BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(@NotNull(message = "Preço Venda") BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public @Length(max = 500, message = "Observação") String getObservacao() {
        return observacao;
    }

    public void setObservacao(@Length(max = 500, message = "Observação") String observacao) {
        this.observacao = observacao;
    }
}
