package jmauriciorlima.com.github.gestao_vendas.controlador;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jmauriciorlima.com.github.gestao_vendas.entidades.Produto;
import jmauriciorlima.com.github.gestao_vendas.servico.ProdutoServico;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria{codigoCategoria}/produto")
@Tag(name = "Produtos", description = "API de produtos")
public class ProdutoControlador {

    private ProdutoServico produtoServico;

    public ProdutoControlador(ProdutoServico produtoServico) {
        this.produtoServico = produtoServico;
    }

    @Operation(summary = "Listar todos os produtos", description = "Lista de todos os produtos cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornadas com sucesso")
    })
    @GetMapping
    public List<Produto> listarTodos(@PathVariable Long codigoCategoria) {
        return produtoServico.listarTodosPorCategoria(codigoCategoria);
    }

    @Operation(summary = "Buscar produto por código", description = "Busca produtos pelo código fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado ou não existe")
    })
    @GetMapping(value = "/{codigoProduto}")
    public ResponseEntity<Produto> buscarPorCodigo(@PathVariable Long codigoCategoria,
                                                   @PathVariable Long codigoProduto) {
        Optional<Produto> produto = produtoServico.buscarPorCodigo(codigoProduto, codigoCategoria);
        return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Salvar produto", description = "Salvar novos produtos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto salvo com sucesso.")
    })
    @PostMapping
    public ResponseEntity<Produto> salvar(@PathVariable Long codigoCategoria,
                                          @Validated  @RequestBody Produto produto) {
        Produto salvar = produtoServico.salvar(codigoCategoria, produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvar);
    }

    @Operation(summary = "Atualizar produto", description = "Atualizar produtos existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso.")
    })
    @PutMapping("/{codigoProduto}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long codigoCategoria,
                                             @PathVariable Long codigoProduto,
                                             @Validated  @RequestBody Produto produto) {
        return ResponseEntity.ok(produtoServico.atualizar(codigoCategoria, codigoProduto, produto));
    }


}
