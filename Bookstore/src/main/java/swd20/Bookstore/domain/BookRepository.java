package swd20.Bookstore.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByTitle(String title);
    Long deleteByTitle(String title);
    Long deleteByAuthor(String author);
}
