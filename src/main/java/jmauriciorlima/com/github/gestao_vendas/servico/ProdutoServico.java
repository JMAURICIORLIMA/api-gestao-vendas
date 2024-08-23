package jmauriciorlima.com.github.gestao_vendas.servico;

import jmauriciorlima.com.github.gestao_vendas.entidades.Produto;
import jmauriciorlima.com.github.gestao_vendas.excecao.RegraNegocioException;
import jmauriciorlima.com.github.gestao_vendas.repositorio.ProdutoRepostirotio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public Produto salvar(Long codigoCategoria, Produto produto) {
        validarCategoriaDoProdutoExiste(codigoCategoria);
        validarProdutoDuplicado(produto);
        return produtoRepostirotio.save(produto);
    }

    public Produto atualizar(Long codigoCategoria, Long codigoProduto, Produto produto) {
        Produto produtoSalvar = validarCategoriaDoProdutoExiste(codigoProduto, codigoCategoria);
        validarCategoriaDoProdutoExiste(codigoCategoria);
        validarProdutoDuplicado(produto);
        BeanUtils.copyProperties(produto, produtoSalvar, "codigo ");
        return produtoRepostirotio.save(produtoSalvar);
    }

    public void deletar(Long codigoCategoria, Long codigoProduto) {
        Produto produto = validarCategoriaDoProdutoExiste(codigoProduto, codigoCategoria);
        produtoRepostirotio.delete(produto);
    }
    private Produto validarCategoriaDoProdutoExiste(Long codigoProduto, Long codigoCategoria) {
        Optional<Produto> produto = buscarPorCodigo(codigoProduto, codigoCategoria);
        if (produto.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return produto.get();
    }

    private void validarProdutoDuplicado(Produto produto) {
        Optional<Produto> produtoPorDescricao = produtoRepostirotio.findByCategoriaCodigoAndDescricao(
                produto.getCategoria().getCodigo(),
                produto.getDescricao());
        if (produtoPorDescricao.isPresent() && produtoPorDescricao.get().getCodigo() != produto.getCodigo()) {
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