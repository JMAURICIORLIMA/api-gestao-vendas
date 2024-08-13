package jmauriciorlima.com.github.gestao_vendas.controlador;

import jmauriciorlima.com.github.gestao_vendas.entidades.Categoria;
import jmauriciorlima.com.github.gestao_vendas.servico.CategoriaServico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaControlador {

    private CategoriaServico categoriaServico;

    public CategoriaControlador(CategoriaServico categoriaServico) {
        this.categoriaServico = categoriaServico;
    }

    @GetMapping
    public List<Categoria> listarTodas() {
        return categoriaServico.listarTodas();
    }

    @GetMapping(value = "/{codigo}")
    public ResponseEntity<Optional<Categoria>> buscarPorId(@PathVariable Long codigo) {
        Optional<Categoria> categoria = categoriaServico.buscarPorId(codigo);
        return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
    }
}
