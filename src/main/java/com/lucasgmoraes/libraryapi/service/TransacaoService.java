package com.lucasgmoraes.libraryapi.service;

import com.lucasgmoraes.libraryapi.model.Autor;
import com.lucasgmoraes.libraryapi.model.GeneroLivro;
import com.lucasgmoraes.libraryapi.model.Livro;
import com.lucasgmoraes.libraryapi.repository.AutorRepository;
import com.lucasgmoraes.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void atualizacaoSemAtualizar(){
        var livro = livroRepository.findById(UUID.fromString("4c530e8b-d7b3-473f-8535-eacb2245013f")).orElse(null);

        livro.setDataPublicacao(LocalDate.of(2024,4,3));
    }

    @Transactional
    public void executar(){
        //salvar autor
        Autor autor = new Autor();
        autor.setNome("Teste Francisco");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        autorRepository.save(autor);

        //salva o livro
        Livro livro = new Livro();
        livro.setIsbn("90581-84546");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Teste Livro da Francisco");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        livro.setAutor(autor);

        livroRepository.save(livro);

        if(autor.getNome().equals("Teste Francisco")){
            throw new RuntimeException("Rollback!");
        }
    }
}
