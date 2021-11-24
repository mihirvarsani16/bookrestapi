package com.bookrestapi.bookrestapi.Services;

import static org.mockito.Mockito.verify;

import com.bookrestapi.bookrestapi.repository.BookRepository;
import com.bookrestapi.bookrestapi.service.BookService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookServicesTest {

    @Mock
    private BookRepository bookRepository;

    // @Autowired
    private BookService bookService;

    @AfterEach
    void tearDown() {
        System.out.println("tearing down");

    }

    @BeforeEach
    void setUp() {
        System.out.println("setting up");
        this.bookService = new BookService(bookRepository);
    }

    @Test
    void getAllBook() {

        bookService.getAllBook();

        verify(bookRepository).findAll();
    }

    @Test
    void findByBid() {
        Long id = (long) 7;
        bookService.getBook(id);

        Long bid = (long) 7;
        verify(bookRepository).findByBid(bid);

        
    }

}
