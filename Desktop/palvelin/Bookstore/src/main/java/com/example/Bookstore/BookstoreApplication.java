package com.example.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.BookstoreApplication;
import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;
import com.example.Bookstore.domain.User;
import com.example.Bookstore.domain.UserRepository;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner bookstreDemo(BookRepository srepository, CategoryRepository drepository, UserRepository urepository) {
		return (args) -> {
			
			log.info("save a couple of books");
			drepository.save(new Category("Sci-Fi"));
			drepository.save(new Category("History"));
			drepository.save(new Category("Comedy"));
			
			srepository.save(new Book("Steel caves", "Isaac Asimov", 1950, "951-98548-9-4", 120.0, drepository.findByName("Sci-Fi").get(0)));
			srepository.save(new Book("Foundation", "Isaac Asimov", 1975, "551-98548-9-4", 99.95, drepository.findByName("Sci-Fi").get(0)));	
			
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@gmail.com", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@gmail.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : srepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
	
}
