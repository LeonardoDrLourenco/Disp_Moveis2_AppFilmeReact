package br.edu.uniara.api.projetoApi.controller;

import br.edu.uniara.api.projetoApi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filme")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Controller {
    @Autowired
    Repository repository;

    @Autowired
    RepositoryCompras repositoryCompras;

    @Autowired
    RepositoryUsuario repositoryUsuario;

    @GetMapping
    public List<Dto> selcionaFilmes(){
        final List<Dto> listaFilmes = repository.findAll();
        return listaFilmes;
    }

    @GetMapping("/historicoCompras")
    public List<DtoCompraIngresso> getHistoricoCompras(){
        final List<DtoCompraIngresso> listaHistoricoCompras = repositoryCompras.findAll();
        return listaHistoricoCompras;
    }

    @GetMapping("/testaUsuario/{usuario}/{senha}")
    public ResponseEntity<String> testarUsuario(@PathVariable String usuario, @PathVariable String senha) {
        final List<DtoUsuario> listaUsuarios = repositoryUsuario.findAll();

        for (DtoUsuario usuarioAtual : listaUsuarios) {
            if (usuarioAtual.getNome().equals(usuario) && usuarioAtual.getSenha().equals(senha)) {
                return ResponseEntity.ok("Usuário autenticado com sucesso!");
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos.");
    }

    @PostMapping("/cadastraUsuario")
    public ResponseEntity<DtoUsuario> cadastraUsuario(
            @RequestBody DtoUsuario usuario
    ) {
        var usuarioSalvo = repositoryUsuario.save(usuario);
        return ResponseEntity.ok(usuarioSalvo);
    }

    @PostMapping
    public ResponseEntity<DtoCompraIngresso> insereCompraFilme(
            @RequestBody DtoCompraIngresso filme
    ) {
        var filmeSalvo = repositoryCompras.save(filme);
        return ResponseEntity.ok(filmeSalvo);
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<Dto> atualizaFilmes(@PathVariable Long id, @RequestBody Dto clienteAtualizado) {
        Dto clienteExistente = repository.findById(id);

        if (clienteExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Atualize os campos relevantes do clienteExistente com os dados do clienteAtualizado
        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setEmail(clienteAtualizado.getEmail());
        // Adicione outros campos conforme necessário

        Dto clienteSalvo = repository.save(clienteExistente); // Salve as alterações no banco de dados

        return ResponseEntity.ok(clienteSalvo);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaFilmes(@PathVariable Long id) {
        boolean filmeExistente = repositoryCompras.existsById(id);
        if (!filmeExistente) {
            return ResponseEntity.notFound().build();
        }
        repositoryCompras.deleteById(id);
        return ResponseEntity.ok(id + "was removed");
    }
}
