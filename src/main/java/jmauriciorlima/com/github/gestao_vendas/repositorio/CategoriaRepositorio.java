package jmauriciorlima.com.github.gestao_vendas.repositorio;

import jmauriciorlima.com.github.gestao_vendas.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
}
