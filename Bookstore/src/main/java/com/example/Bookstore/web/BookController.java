package com.example.Bookstore.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.CategoryRepository;



@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository drepository;
	
	@RequestMapping(value= {"/", "/booklist", "edit/booklist"})
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";	
	}
	
	@RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categorys", drepository.findAll());
        return "addbook";
    }
	
	
	@RequestMapping(value = {"/save", "edit/save"}, method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    }
	
	@RequestMapping(value = "/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId) {
    	repository.deleteById(bookId);
    	return "redirect:../booklist";
    }
	
	@RequestMapping(value = "/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model){
	model.addAttribute("book", repository.findById(bookId));
	model.addAttribute("categorys", drepository.findAll());
	repository.deleteById(bookId);
	return "addbook";
	}
         
}

