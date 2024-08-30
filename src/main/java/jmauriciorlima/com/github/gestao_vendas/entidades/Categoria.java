package jmauriciorlima.com.github.gestao_vendas.entidades;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    @Schema(name = "codigo", description = "Código")
    private Long codigo;

    @Column(name = "nome")
    @Schema(name = "nome", description = "Nome")
    private String nome;

    public Categoria() {
    }

    public Categoria(Long codigo) {
        this.codigo = codigo;
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria(Long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria categoria)) return false;
        return Objects.equals(codigo, categoria.codigo) && Objects.equals(nome, categoria.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nome);
    }
}
