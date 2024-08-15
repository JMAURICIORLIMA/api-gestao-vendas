package jmauriciorlima.com.github.gestao_vendas.repositorio;

import jmauriciorlima.com.github.gestao_vendas.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {


    Categoria findByNome(String nome);
}
