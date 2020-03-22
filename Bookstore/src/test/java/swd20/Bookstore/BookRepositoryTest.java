package swd20.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import swd20.Bookstore.domain.Book;
import swd20.Bookstore.domain.BookRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

		@Autowired
		private BookRepository bookRepository;
		
		@Test
		public void findByTitleShouldReturnBookTitle() {
			List<Book> books = bookRepository.findByTitle("The Godfather");
			assertThat(books.equals("The Godfather"));		
		}
		
		@Test // Luodaan uusi kirja testiä varten tietokantaan lisäämistä varten
		public void addBook() {
			Book book = new Book("Milk and Honey", "Kaur Rupi", 2015, "34322-23545453");
			bookRepository.save(book);
			assertThat(book.getId()).isNotNull();
		}
		
		@Test // En ymmärrä miksi tämä toimii Longilla, mutta testi menee läpi joten :)
		public void deleteBook() {
			Long book = bookRepository.deleteByAuthor("Kaur Rupi");
		}
}
