package com.lucasgmoraes.libraryapi.repository;

import com.lucasgmoraes.libraryapi.model.Autor;
import com.lucasgmoraes.libraryapi.model.GeneroLivro;
import com.lucasgmoraes.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("99781-82566");
        livro.setPreco(BigDecimal.valueOf(500));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Ciencias");
        livro.setDataPublicacao(LocalDate.of(1978, 1, 26));

        Autor autor = autorRepository.findById(
                UUID.fromString("d59eb56b-331c-4806-be97-6c90a0f7a729")).orElse(null);

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarAutorELivroTest(){
        Livro livro = new Livro();
        livro.setIsbn("90581-84546");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Outro livro");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("José");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        autorRepository.save(autor);

        livro.setAutor(autor);

        repository.save(livro);
    }


    @Test
    void salvarCascadeTest(){
        Livro livro = new Livro();
        livro.setIsbn("90881-87546");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void atualizarAutorTest(){
        UUID id = UUID.fromString("ac8e950c-f669-48c9-9958-cfb4a3b41f97");
        var livro = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("891c8cc3-0611-4918-bd26-79c11ee1f80b");
        Autor autor = autorRepository.findById(idAutor).orElse(null);

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void deletarTest(){
        UUID id = UUID.fromString("ac8e950c-f669-48c9-9958-cfb4a3b41f97");
        repository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTest(){
        UUID id = UUID.fromString("4c530e8b-d7b3-473f-8535-eacb2245013f");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Livro:");
        System.out.println(livro.getTitulo());
        System.out.println("Autor:");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void pesquisaPorTituloTest(){
        List<Livro> lista = repository.findByTitulo("O roubo do carro preto");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorIsbnTest(){
        Optional<Livro> livro = repository.findByIsbn("90651-89546");
        livro.ifPresent(System.out::println);
    }

    @Test
    void pesquisaPorTituloAndPrecoTest(){
        List<Livro> lista = repository.findByTituloAndPreco("Outro livro", BigDecimal.valueOf(100));
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivrosComQueryJPQLTest(){
        var resultado = repository.listarTodosOrdenadoPorTituloAndPreco();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivros(){
        var resultado = repository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarDiferentesTitulos(){
        var resultado = repository.listarDiferentesTitulos();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosAutoresBraileiros(){
        var resultado = repository.listarGenerosAutoresBraileiros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroQueryParam(){
        var resultado = repository.findByGenero(GeneroLivro.FICCAO, "dataPublicacao");
        resultado.forEach(System.out::println);

    }@Test
    void listarPorGeneroPositionalParam(){
        var resultado = repository.findByGeneroPositionalParameters(GeneroLivro.FICCAO, "dataPublicacao");
        resultado.forEach(System.out::println);
    }

    @Test
    void deletePorGeneroTest(){
        repository.deleteByGenero(GeneroLivro.CIENCIA);
    }

    @Test
    void updateDataPublicacaoTest(){
        repository.updateDataPublicacao(LocalDate.of(2000, 1, 1));
    }
}