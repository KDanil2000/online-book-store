package bookstore;

import java.math.BigDecimal;
import model.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import service.BookService;

@SpringBootApplication
@ComponentScan(basePackages = {"repository", "service", "bookstore"})
@EntityScan(basePackages = "model")
public class OnlineBookStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineBookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(BookService bookService) {
        return args -> {
            Book firstBook = new Book();
            firstBook.setAuthor("Some Author");
            firstBook.setTitle("Some Title");
            firstBook.setPrice(BigDecimal.valueOf(150));
            firstBook.setIsbn("980745631");
            bookService.save(firstBook);
            System.out.println(bookService.findAll());
        };
    }
}
