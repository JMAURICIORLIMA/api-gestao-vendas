package jmauriciorlima.com.github.gestao_vendas.controlador;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jmauriciorlima.com.github.gestao_vendas.entidades.Categoria;
import jmauriciorlima.com.github.gestao_vendas.servico.CategoriaServico;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@Tag(name = "Categorias", description = "API de categorias.")
public class CategoriaControlador {

    private CategoriaServico categoriaServico;

    public CategoriaControlador(CategoriaServico categoriaServico) {
        this.categoriaServico = categoriaServico;
    }

    @Operation(summary = "Listar todas as categorias", description = "Lista de todas as categorias cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de categorias retornadas com sucesso")
    })
    @GetMapping
    public List<Categoria> listarTodas() {
        return categoriaServico.listarTodas();
    }

    @Operation(summary = "Buscar categoria por código", description = "Busca categoria pelo código fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada ou não existe")
    })
    @GetMapping(value = "/{codigo}")
    public ResponseEntity<Categoria> buscarPorCodigo(@PathVariable Long codigo) {
        return categoriaServico.buscarPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Salvar categoria", description = "Cria uma nova categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping
    public ResponseEntity<Categoria> salvar(@Valid @RequestBody Categoria categoria) {
        Categoria categoriaSalva = categoriaServico.salvar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @Operation(summary = "Atualizar", description = "Atualiza os dados de uma categoria existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados fornecidos inválidos"),
    })
    @PutMapping("/{codigo}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long codigo, @Valid @RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaServico.atualizar(codigo, categoria));
    }

    @Operation(summary = "Excluir", description = "Excluir categoria por código.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados fornecidos inválidos"),
    })
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo) {
        categoriaServico.excluir(codigo);
    }

}