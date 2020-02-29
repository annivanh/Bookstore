package swd20.Bookstore.webcontrol;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import swd20.Bookstore.domain.Book;
import swd20.Bookstore.domain.BookRepository;

@Controller
public class BookController {

	@Autowired
	BookRepository BookRepository;
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String showBooks(Model model) {

		List<Book> books = (List<Book>) BookRepository.findAll();
		model.addAttribute("book", new Book());
		model.addAttribute("books", books);
		return "booklist";
	}

	@RequestMapping(value = "/newbook", method = RequestMethod.GET)
	public String addNewBook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute Book book) {
		BookRepository.save(book);
		return "redirect:booklist";
	}

	@RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId) {
		BookRepository.deleteById(bookId);
		return "redirect:../booklist";
	}

	@GetMapping(value = "/editbook/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		Optional<Book> book = BookRepository.findById(bookId);
		model.addAttribute("book", book);
		return "editbook";
	}

	@RequestMapping(value = "/editbook/{id}", method = RequestMethod.POST)
	public String save(@ModelAttribute Book book) {
		BookRepository.save(book);
		return "redirect:/booklist";
	}

}