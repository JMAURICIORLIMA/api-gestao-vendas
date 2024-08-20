package jmauriciorlima.com.github.gestao_vendas.servico;

import jmauriciorlima.com.github.gestao_vendas.entidades.Categoria;
import jmauriciorlima.com.github.gestao_vendas.entidades.Produto;
import jmauriciorlima.com.github.gestao_vendas.excecao.RegraNegocioException;
import jmauriciorlima.com.github.gestao_vendas.repositorio.ProdutoRepostirotio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServico {

    @Autowired
    private CategoriaServico categoriaServico;

    @Autowired
    private ProdutoRepostirotio produtoRepostirotio;

    public List<Produto> listarTodosPorCategoria(Long condigoCategoria) {
        return produtoRepostirotio.findByCategoriaCodigo(condigoCategoria);
    }

    public Optional<Produto> buscarPorCodigo(Long codigoProduto, Long codigoCategoria) {
        return produtoRepostirotio.buscarPorCodigo(codigoProduto, codigoCategoria);
    }

    public Produto salvar(Produto produto) {
        validarCategoriaDoProdutoExiste(produto.getCategoria().getCodigo());
        validarProdutoDuplicado(produto);
        return produtoRepostirotio.save(produto);
    }

    private void validarProdutoDuplicado(Produto produto) {
        if (produtoRepostirotio.findByCategoriaCodigoAndDescricao(
                produto.getCategoria().getCodigo(),
                produto.getDescricao()).isPresent()) {
            throw new RegraNegocioException(String.format("O produto %s já está cadastrado.", produto.getDescricao()));
        }
    }

    private void validarCategoriaDoProdutoExiste(Long codigoCategoria) {
        if (codigoCategoria == null) {
            throw new RegraNegocioException("A categoria não pode ser nula");
        }
        if (categoriaServico.buscarPorCodigo(codigoCategoria).isEmpty()) {
            throw  new RegraNegocioException(
                    String.format("A categoria de código %s informada não existe no cadastro", codigoCategoria));
        }

    }
}