package br.com.alura.literalura.principal;

import br.com.alura.literalura.models.Autor;
import br.com.alura.literalura.models.Livro;
import br.com.alura.literalura.Repository.AutorRepository;
import br.com.alura.literalura.Repository.LivroRepository;
import br.com.alura.literalura.services.ConsumoApi;
import br.com.alura.literalura.services.ConverteDados;
import br.com.alura.literalura.models.Dados;
import br.com.alura.literalura.models.DadosAutor;
import br.com.alura.literalura.models.DadosLivro;

import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository){
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu(){

        int opcao = -1;

        while(opcao != 0){

            System.out.println("""
                    
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em determinado ano
                    5 - Listar livros por idioma
                    
                    0 - Sair
                    
                    """);

            opcao = leitura.nextInt();
            leitura.nextLine();

            switch(opcao){

                case 1:
                    buscarLivro();
                    break;

                case 2:
                    listarLivros();
                    break;

                case 3:
                    listarAutores();
                    break;

                case 4:
                    autoresVivos();
                    break;

                case 5:
                    livrosPorIdioma();
                    break;
            }
        }
    }

    private void buscarLivro(){

        System.out.println("Digite o nome do livro:");
        var titulo = leitura.nextLine();

        String endereco = "https://gutendex.com/books/?search=" + titulo.replace(" ", "%20");

        ConsumoApi consumoApi = new ConsumoApi();
        String json = consumoApi.obterDados(endereco);

        ConverteDados conversor = new ConverteDados();
        Dados dados = conversor.obterDados(json, Dados.class);

        if(dados.results().size() > 0){

            DadosLivro dadosLivro = dados.results().get(0);

            // CORRIGIDO: agora salva anoNascimento e anoFalecimento do autor
            DadosAutor dadosAutor = dadosLivro.autores().get(0);
            Autor autor = new Autor(
                    dadosAutor.nome(),
                    dadosAutor.anoNascimento(),
                    dadosAutor.anoFalecimento()
            );

            Livro livro = new Livro(
                    dadosLivro.titulo(),
                    dadosLivro.idioma().get(0),
                    dadosLivro.downloads(),
                    autor
            );

            autorRepository.save(autor);
            livroRepository.save(livro);

            System.out.println("Livro salvo com sucesso!");
            System.out.println(livro);

        } else {

            System.out.println("Livro não encontrado.");

        }
    }

    private void listarLivros(){

        List<Livro> livros = livroRepository.findAll();

        livros.forEach(l ->
                System.out.println(l.getTitulo() +
                        " - " +
                        l.getAutor().getNome()));
    }

    private void listarAutores(){

        List<Autor> autores = autorRepository.findAll();

        autores.forEach(a ->
                System.out.println(a.getNome() +
                        " (" + a.getAnoNascimento() + " - " + a.getAnoFalecimento() + ")"));
    }

    private void autoresVivos(){

        System.out.println("Digite o ano:");

        int ano = leitura.nextInt();

        List<Autor> autores =
                autorRepository
                        .findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(ano, ano);

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado para o ano " + ano + ".");
        } else {
            autores.forEach(a ->
                    System.out.println(a.getNome() +
                            " (" + a.getAnoNascimento() + " - " + a.getAnoFalecimento() + ")"));
        }
    }

    private void livrosPorIdioma(){

        System.out.println("""
            Idiomas disponíveis:
            en - inglês
            es - espanhol
            fr - francês
            pt - português
            """);

        String idioma = leitura.nextLine();

        List<Livro> livros = livroRepository.findByIdioma(idioma);

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma: " + idioma);
        } else {
            livros.forEach(l ->
                    System.out.println(l.getTitulo()));
        }
    }
}
