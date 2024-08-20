package jmauriciorlima.com.github.gestao_vendas.servico;

import jmauriciorlima.com.github.gestao_vendas.entidades.Produto;
import jmauriciorlima.com.github.gestao_vendas.repositorio.ProdutoRepostirotio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServico {

    private ProdutoRepostirotio produtoRepostirotio;

    public ProdutoServico(ProdutoRepostirotio produtoRepostirotio) {
        this.produtoRepostirotio = produtoRepostirotio;
    }

    public List<Produto> listarTodosPorCategoria(Long condigoCategoria) {
        return produtoRepostirotio.findByCategoriaCodigo(condigoCategoria);
    }

    public Optional<Produto> buscarPorCodigo(Long codigoProduto, Long codigoCategoria) {
        return produtoRepostirotio.buscarPorCodigo(codigoProduto, codigoCategoria);
    }

    public Produto salvar(Produto produto) {
        return produtoRepostirotio.save(produto);
    }
}