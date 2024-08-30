package jmauriciorlima.com.github.gestao_vendas.servico;

import jmauriciorlima.com.github.gestao_vendas.entidades.Categoria;
import jmauriciorlima.com.github.gestao_vendas.excecao.RegraNegocioException;
import jmauriciorlima.com.github.gestao_vendas.repositorio.CategoriaRepositorio;
import jmauriciorlima.com.github.gestao_vendas.dto.categoria.CategoriaResponseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaServico {

    private CategoriaRepositorio categoriaRepositorio;

    public CategoriaServico(CategoriaRepositorio categoriaRepositorio) {
        this.categoriaRepositorio = categoriaRepositorio;
    }

    public List<CategoriaResponseDTO> listarTodas() {
        return categoriaRepositorio.findAll().stream()
                .map(CategoriaResponseDTO::converterParaCategoriaDTO)
                .collect(Collectors.toList());
    }

    public Optional<CategoriaResponseDTO> buscarPorCodigo(Long codigo) {
        return categoriaRepositorio.findById(codigo)
                .map(CategoriaResponseDTO::converterParaCategoriaDTO);
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
        validarCategoriaExiste(codigo);
        categoriaRepositorio.deleteById(codigo);
    }

    private Categoria validarCategoriaExiste(Long codigo) {
        return categoriaRepositorio.findById(codigo)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    private void validarCategoriaDuplicada(Categoria categoria) {
        Categoria categoriaEncontrada = categoriaRepositorio.findByNome(categoria.getNome());
        if ((categoriaEncontrada != null) && (!Objects.equals(categoriaEncontrada.getCodigo(), categoria.getCodigo()))) {
            throw new RegraNegocioException(
                    String.format("A categoria %s já está cadastrada", categoria.getNome().toUpperCase()));
        }
    }
}
