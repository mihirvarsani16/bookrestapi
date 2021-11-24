package com.bookrestapi.bookrestapi.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Random;

import com.bookrestapi.bookrestapi.entity.Book;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SpringBootBootstrapLiveTest {

    private final String API = "http://localhost:8080/";

    private final String API_ROOT = "http://localhost:8080/all-book";

    private final String API_ROOT_ONE = "http://localhost:8080/6";

    private Book createRandomBook() {
        Book book = new Book();
        book.setTitle(RandomStringUtils.random(5));
        book.setAuthor(RandomStringUtils.random(10));
        return book;
    }

    // private String createBookAsUri(Book book) {
    // Response response = RestAssured.given()
    // .contentType(MediaType.APPLICATION_JSON_VALUE)
    // .body(book)
    // .post(API);
    // return API + "/" + response.jsonPath().get("id");
    // }

    @Test
    public void whenGetAllBooks_thenOK() {
        Response response = RestAssured.get(API_ROOT);

        assertEquals(HttpStatus.FOUND.value(), response.getStatusCode());

    }

    @Test
    public void getOneBook() {
        Response response = RestAssured.get(API_ROOT_ONE);
        // System.out.println("response " + response);
        assertEquals(HttpStatus.FOUND.value(), response.getStatusCode());
    }

    @Test
    public void notfountBook() {
        Response response = RestAssured.get(API + new Random().nextInt(9));
        // System.out.println("response " + response);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    @Test
    public void createBook() {
        Book book = createRandomBook();
        System.out.println("book " + book);
        Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(book).post(API);

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

    @Test
    public void problemInCreateBook() {
        Book book = createRandomBook();
        book.setAuthor(null);
        Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(book).post(API);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCode());

    }

    @Test
    public void updateBook() {
        Book book = createRandomBook();
        book.setBid(7);
        Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(book).put(API);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

    }

    @Test
    public void notUpdateBook() {
        Book book = createRandomBook();

        Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(book).put(API);

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());

    }

    @Test
    public void delete() {
        Response response = RestAssured.given().delete(API + 7);

        assertEquals(HttpStatus.OK.value(), response.statusCode());
    }

    @Test
    public void notDelete() {
        Response response = RestAssured.given().delete(API + 7);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
    }

}
