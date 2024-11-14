package br.edu.uniara.api.projetoApi.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilmesDadosFixos {
    private static final Logger log = LoggerFactory.getLogger(FilmesDadosFixos.class);

    @Bean
    CommandLineRunner initDatabase(Repository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Dto("Star wars", "Sci-fi", "R$25,00","https://acdn.mitiendanube.com/stores/363/165/products/star-wars-revenge-of-the-sith-episode-iii21-745553973b7d912c1516511071008343-480-0.webp")));
            log.info("Preloading " + repository.save(new Dto("Harry potter", "Fantasia", "R$20,00","https://m.media-amazon.com/images/I/71GU+wvLFdL._AC_UF894,1000_QL80_.jpg")));
            log.info("Preloading " + repository.save(new Dto("Joker 2", "Drama", "R$22,00","https://sportshub.cbsistatic.com/i/2024/08/19/9a15da04-fb52-4f9c-9980-63f1f51cdfdf/joker-folie-a-deux-movie-poster.jpg?auto=webp&width=1080&height=1350&crop=0.8:1,smart")));
            log.info("Preloading " + repository.save(new Dto("Beetlejuice", "Terror", "R$24,00","https://acdn.mitiendanube.com/stores/363/165/products/beetlejuice1-75156bc24654bc1e8116511090375217-640-0.jpg")));
        };
    }
}
