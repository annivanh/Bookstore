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
import org.springframework.web.bind.annotation.ResponseBody;

import swd20.Bookstore.domain.Book;
import swd20.Bookstore.domain.BookRepository;
import swd20.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	BookRepository bookRepository;
	@Autowired 
	CategoryRepository categoryRepository;
	
	@RequestMapping(value = "/booklist")
	public String showBooks(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist";
	}
	
	//REST show books
	@RequestMapping(value="/books", method=RequestMethod.GET)
	public @ResponseBody List<Book> booklistRest(){
		return (List<Book>) bookRepository.findAll();
	}
	
	//REST show book by id
	@RequestMapping(value="/books/{id}", method=RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId){ 
	return bookRepository.findById(bookId);
	}

	@RequestMapping(value = "/newbook")
	public String addNewBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryRepository.findAll());
		return "addbook";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute Book book) {
		bookRepository.save(book);
		return "redirect:booklist";
	}

	@RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId) {
		bookRepository.deleteById(bookId);
		return "redirect:../booklist";
	}

	@GetMapping(value = "/editbook/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		Optional<Book> book = bookRepository.findById(bookId);
		model.addAttribute("book", book);
		model.addAttribute("categories", categoryRepository.findAll());
		return "editbook";
	}

	@RequestMapping(value = "/editbook/{id}", method = RequestMethod.POST)
	public String save(@ModelAttribute Book book) {
		bookRepository.save(book);
		return "redirect:/booklist";
	}

}