package swd20.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import swd20.Bookstore.domain.Book;
import swd20.Bookstore.domain.BookRepository;
import swd20.Bookstore.domain.Category;
import swd20.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {

			log.info("save a couple of books");
			categoryRepository.save(new Category("Non-Fiction"));
			categoryRepository.save(new Category("Fiction"));
			categoryRepository.save(new Category("Poetry"));
			categoryRepository.save(new Category("Biography"));
			
			bookRepository.save(new Book("Thinking About It Only Makes It Worse", "Mitchell David", 2014, "2013121-32"));
			bookRepository.save(new Book("The Godfather", "Puzo Mario", 1959, "93049101-89"));
			bookRepository.save(new Book("The Flame", "Cohen Leonard", 2017, "40520234-19"));
			
			
			log.info("fetch all categories");
			for (Category category : categoryRepository.findAll()) {
				log.info(category.toString());
			}

			log.info("fetch all books");

			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}