package jmauriciorlima.com.github.gestao_vendas.servico;

import jmauriciorlima.com.github.gestao_vendas.entidades.Categoria;
import jmauriciorlima.com.github.gestao_vendas.excecao.RegraNegocioException;
import jmauriciorlima.com.github.gestao_vendas.repositorio.CategoriaRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoriaServico {

    private CategoriaRepositorio categoriaRepositorio;

    public CategoriaServico(CategoriaRepositorio categoriaRepositorio) {
        this.categoriaRepositorio = categoriaRepositorio;
    }

    public List<Categoria> listarTodas() {
        return categoriaRepositorio.findAll();
    }

    public Optional<Categoria> buscarPorCodigo(Long codigo) {
        return categoriaRepositorio.findById(codigo);
    }

    public Categoria salvar(Categoria categoria) {
        validarCategoriaDuplicada(categoria);
        return categoriaRepositorio.save(categoria);
    }

    public Categoria atualizar(Long codigo, Categoria categoria) {
        Categoria categoriaSalvar = validarCategoriaExiste(codigo);
        if (!categoria.getNome().equals(categoriaSalvar.getNome())) {
            validarCategoriaDuplicada(categoria);
        }

        BeanUtils.copyProperties(categoria, categoriaSalvar, "codigo");
        return categoriaRepositorio.save(categoriaSalvar);
    }

    public void excluir(Long codigo) {
        Optional<Categoria> categoria = buscarPorCodigo(codigo);
        if (!categoria.isPresent()) {
            throw new EmptyResultDataAccessException("Categoria de código " + codigo + " não encontrada", 0);
        }

        categoriaRepositorio.deleteById(codigo);
    }

    private Categoria validarCategoriaExiste(Long codigo) {
        Optional<Categoria> categoria = buscarPorCodigo(codigo);
        if (categoria.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return categoria.get();
    }

    private void validarCategoriaDuplicada(Categoria categoria) {
        Categoria categoriaEncontrada = categoriaRepositorio.findByNome(categoria.getNome());
        if ((categoriaEncontrada != null) && (!Objects.equals(categoriaEncontrada.getCodigo(), categoria.getCodigo()))) {
            throw new RegraNegocioException(
                    String.format("A categoria %s já está cadastrada", categoria.getNome().toUpperCase()));
        }
    }
}
