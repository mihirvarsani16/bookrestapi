package com.bookrestapi.bookrestapi.helper;

public class BookNotFoundException extends Exception {

    public BookNotFoundException() {
        super("Book is not found by this id!!");
    }

}
