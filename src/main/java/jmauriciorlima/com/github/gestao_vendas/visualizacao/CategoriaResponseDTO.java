package jmauriciorlima.com.github.gestao_vendas.visualizacao;

import io.swagger.v3.oas.annotations.media.Schema;
import jmauriciorlima.com.github.gestao_vendas.entidades.Categoria;

public class CategoriaResponseDTO {

    @Schema(description = "Código")
    private Long codigo;

    @Schema(description = "Nome")
    private String nome;

    public CategoriaResponseDTO(Long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public static CategoriaResponseDTO converterParaCategoriaDTO(Categoria categoria) {
        return new CategoriaResponseDTO(categoria.getCodigo(), categoria.getNome());
    }

    public Long getCodigo() {
        return codigo;
    }


    public String getNome() {
        return nome;
    }

}
