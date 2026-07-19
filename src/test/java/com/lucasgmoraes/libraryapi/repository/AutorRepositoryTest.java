package com.lucasgmoraes.libraryapi.repository;

import com.lucasgmoraes.libraryapi.model.Autor;
import com.lucasgmoraes.libraryapi.model.GeneroLivro;
import com.lucasgmoraes.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        var autorsalvo = repository.save(autor);
        System.out.println("Autor salvo: " + autorsalvo);
    }

    @Test
    public void atualizarTest() {
       var id = UUID.fromString("e12acc2e-ad0f-4512-85ba-b9fe898a6924");

       Optional<Autor> possivelAutor = repository.findById(id);

       if(possivelAutor.isPresent()) {
           Autor autorEncontrado = possivelAutor.get();
           System.out.println("Dados do autor:");
           System.out.println(autorEncontrado);

           autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30));

           repository.save(autorEncontrado);
       }
    }

    @Test
    public void listarTest() {
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("1e9a076a-47c5-4d49-89aa-938d8a119b92");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("1bcb2d35-64b7-43b3-a25d-3a9df0579a0d");
        var maria = repository.findById(id).get();
        repository.delete(maria);
    }

    @Test
    void salavarAutorComLivroaTest(){
        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1984, 9, 5));

        Livro livro = new Livro();
        livro.setIsbn("90121-89546");
        livro.setPreco(BigDecimal.valueOf(180));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("O roubo do carro preto");
        livro.setDataPublicacao(LocalDate.of(1999, 6, 9));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("90651-89546");
        livro2.setPreco(BigDecimal.valueOf(200));
        livro2.setGenero(GeneroLivro.MISTERIO);
        livro2.setTitulo("Acharam o carro preto");
        livro2.setDataPublicacao(LocalDate.of(2000, 8, 9));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);
        livroRepository.saveAll(autor.getLivros());
    }

    @Test
    void listarLivrosTest(){
        var id = UUID.fromString("cc5bf7cc-9a80-4848-b670-840fe9cb66ca");
        var autor = repository.findById(id).get();

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);
    }
}
