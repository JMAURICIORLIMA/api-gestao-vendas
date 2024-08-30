package jmauriciorlima.com.github.gestao_vendas.controlador;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jmauriciorlima.com.github.gestao_vendas.dto.produto.ProdutoRequestDTO;
import jmauriciorlima.com.github.gestao_vendas.dto.produto.ProdutoResponseDTO;
import jmauriciorlima.com.github.gestao_vendas.entidades.Produto;
import jmauriciorlima.com.github.gestao_vendas.servico.ProdutoServico;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categoria{codigoCategoria}/produto")
@Tag(name = "Produtos", description = "API de produtos")
public class ProdutoControlador {

    private final ProdutoServico produtoServico;

    public ProdutoControlador(ProdutoServico produtoServico) {
        this.produtoServico = produtoServico;
    }

    @Operation(summary = "Listar todos os produtos", description = "Lista de todos os produtos cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornadas com sucesso")
    })
    @GetMapping
    public List<ProdutoResponseDTO> listarTodos(@PathVariable Long codigoCategoria) {
        return produtoServico.listarTodosPorCategoria(codigoCategoria).stream()
                .map(ProdutoResponseDTO::converterParaProdutoDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Buscar produto por código", description = "Busca produtos pelo código fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado ou não existe")
    })
    @GetMapping(value = "/{codigoProduto}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorCodigo(@PathVariable Long codigoCategoria,
                                                              @PathVariable Long codigoProduto) {
        Optional<Produto> produto = produtoServico.buscarPorCodigo(codigoProduto, codigoCategoria);
        return produto.map(value -> ResponseEntity.ok(ProdutoResponseDTO.converterParaProdutoDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Salvar produto", description = "Salvar novos produtos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto salvo com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro no servidor.")
    })
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvar(@PathVariable Long codigoCategoria,
                                                     @Validated @RequestBody ProdutoRequestDTO produto) {
        Produto produtoSalvar = produtoServico.salvar(codigoCategoria, produto.converterParaEntidade(codigoCategoria));
        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoResponseDTO.converterParaProdutoDTO(produtoSalvar));
    }

    @Operation(summary = "Atualizar produto", description = "Atualizar produtos existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso.")
    })
    @PutMapping("/{codigoProduto}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long codigoCategoria,
                                                        @PathVariable Long codigoProduto,
                                                        @Validated @RequestBody ProdutoRequestDTO produto) {
        Produto produtoAtualizado = produtoServico.atualizar(codigoCategoria, codigoProduto,
                produto.converterParaEntidade(codigoCategoria, codigoProduto));
        return ResponseEntity.ok(ProdutoResponseDTO.converterParaProdutoDTO(produtoAtualizado));
    }

    @Operation(summary = "Deletar produto", description = "Deletar produto existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso.")
    })
    @DeleteMapping("/{codigoProduto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long codigoCategoria,
                        @PathVariable Long codigoProduto) {
        produtoServico.deletar(codigoCategoria, codigoProduto);
    }

}
