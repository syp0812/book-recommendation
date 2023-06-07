package com.example.apimodule.book.controller;

import com.example.coremodule.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/api")
    public void callApi() throws IOException, JSONException {
        bookService.callApi();
    }
}
