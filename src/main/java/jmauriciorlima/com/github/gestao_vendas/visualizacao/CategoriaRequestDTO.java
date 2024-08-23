package jmauriciorlima.com.github.gestao_vendas.visualizacao;

import jakarta.validation.constraints.NotBlank;
import jmauriciorlima.com.github.gestao_vendas.entidades.Categoria;
import org.hibernate.validator.constraints.Length;

public class CategoriaRequestDTO {

    @NotBlank(message = "Nome")
    @Length(min = 3, max = 50, message = "Nome")
    private String nome;

    public Categoria converterParaEntidade() {
        return new Categoria(nome);
    }

    public Categoria converterParaEntidade(Long codigo) {
        return new Categoria(codigo, nome);
    }

    public @NotBlank(message = "Nome") @Length(min = 3, max = 50, message = "Nome") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "Nome") @Length(min = 3, max = 50, message = "Nome") String nome) {
        this.nome = nome;
    }
}
