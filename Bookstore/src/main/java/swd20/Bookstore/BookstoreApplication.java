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
import swd20.Bookstore.domain.User;
import swd20.Bookstore.domain.UserRepository;


@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
		return (args) -> {

			log.info("save a couple of books");
			categoryRepository.save(new Category("Non-Fiction"));
			categoryRepository.save(new Category("Fiction"));
			categoryRepository.save(new Category("Poetry"));
			categoryRepository.save(new Category("Biography"));
			
			bookRepository.save(new Book("Thinking About It Only Makes It Worse", "Mitchell David", 2014, "2013121-32",
					categoryRepository.findByName("Non-Fiction").get(0)));
			bookRepository.save(new Book("The Godfather", "Puzo Mario", 1959, "93049101-89",
					categoryRepository.findByName("Fiction").get(0)));
			bookRepository.save(new Book("The Flame", "Cohen Leonard", 2017, "40520234-19",
					categoryRepository.findByName("Poetry").get(0)));
			
			// käyttäjät= user/password1, admin/password2
			User user1 = new User("user", "$2a$12$NgAlhTafSyfbrpMhlpAKxecGlugKXtnci3c/W/14bWQB18AnxQU1i", "USER");
			User user2 = new User("admin", "$2a$12$r5veyncam2sqYFoEa0xFrOC6km3I9d77LzZy.2h.WZFqzZlK94DTO", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);
			
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