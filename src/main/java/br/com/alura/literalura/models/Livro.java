package br.com.alura.literalura.models;

import jakarta.persistence.*;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String idioma;
    private Double downloads;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Livro(){}

    public Livro(String titulo, String idioma, Double downloads, Autor autor){
        this.titulo = titulo;
        this.idioma = idioma;
        this.downloads = downloads;
        this.autor = autor;
    }

    // CORRIGIDO: construtor com Integer agora atribui os campos corretamente
    public Livro(String titulo, String idioma, Integer downloads, Autor autor) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.downloads = downloads != null ? downloads.doubleValue() : 0.0;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public Autor getAutor() {
        return autor;
    }

    public Double getDownloads() {
        return downloads;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", idioma='" + idioma + '\'' +
                ", downloads=" + downloads +
                ", autor=" + (autor != null ? autor.getNome() : "sem autor") +
                '}';
    }
}
