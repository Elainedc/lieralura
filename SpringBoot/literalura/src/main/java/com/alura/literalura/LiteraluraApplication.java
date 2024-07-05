package com.alura.literalura;

import com.alura.literalura.model.Author;
import com.alura.literalura.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	@Autowired
	private RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Escolha uma opção:");
			System.out.println("1 - Buscar livro por título");
			System.out.println("2 - Listar livros registrados");
			System.out.println("3 - Listar autores");
			System.out.println("4 - Listar autores vivos em determinado ano");
			System.out.println("5 - Listar livros em determinado idioma");
			System.out.println("6 - Top 10 livros mais baixados");
			System.out.println("7 - Buscar autor por nome");
			System.out.println("0 - Sair");
			int opcao = scanner.nextInt();
			scanner.nextLine(); // Consome a nova linha

			switch (opcao) {
				case 1:
					System.out.println("Digite o título do livro:");
					String titulo = scanner.nextLine();
					Book book = restTemplate.getForObject("http://localhost:8080/books/search?title=" + titulo, Book.class);
					if (book != null) {
						System.out.println("Livro encontrado: " + book);
					} else {
						System.out.println("Livro não encontrado.");
					}
					break;
				case 2:
					List<Book> books = restTemplate.getForObject("http://localhost:8080/books", List.class);
					books.forEach(System.out::println);
					break;
				case 3:
					List<Author> authors = restTemplate.getForObject("http://localhost:8080/authors", List.class);
					authors.forEach(System.out::println);
					break;
				case 4:
					System.out.println("Digite o ano:");
					int ano = scanner.nextInt();
					List<Author> authorsByYear = restTemplate.getForObject("http://localhost:8080/authors/alive?year=" + ano, List.class);
					authorsByYear.forEach(System.out::println);
					break;
				case 5:
					System.out.println("Digite o idioma (portuguese, spanish, english, french):");
					String idioma = scanner.nextLine();
					List<Book> booksByLanguage = restTemplate.getForObject("http://localhost:8080/books/language?language=" + idioma, List.class);
					booksByLanguage.forEach(System.out::println);
					break;
				case 6:
					List<Book> topBooks = restTemplate.getForObject("http://localhost:8080/books/top10", List.class);
					topBooks.forEach(System.out::println);
					break;
				case 7:
					System.out.println("Digite o nome do autor:");
					String authorName = scanner.nextLine();
					Author author = restTemplate.getForObject("http://localhost:8080/authors/search?name=" + authorName, Author.class);
					if (author != null) {
						System.out.println("Autor encontrado: " + author);
					} else {
						System.out.println("Autor não encontrado.");
					}
					break;
				case 0:
					System.out.println("Saindo...");
					System.exit(0);
				default:
					System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}
}



