package com.example.coremodule.book.service;

import com.example.coremodule.book.domain.Book;
import com.example.coremodule.book.domain.BookRepository;
import com.example.coremodule.book.dto.BookRequestDto;
import com.example.coremodule.common.Status;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public String callApi() throws IOException, JSONException {

        StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/6260000/NewBookListService/getNewBookList");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + "인증키(decord)");
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("3000", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("resultType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setRequestProperty("Accept", "application/json");

        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        JSONObject jsonObject = new JSONObject(sb.toString());
        JSONObject bodyObject = jsonObject.getJSONObject("getBookNewList").getJSONObject("body");
        System.out.println(bodyObject);
        JSONObject items = bodyObject.getJSONObject("items");
        JSONArray jsonArray = items.getJSONArray("item");

        int i = 0;
        while (i < jsonArray.length()) {
            JSONObject item = jsonArray.getJSONObject(i);

            String title = item.getString("title_info");
            String publisher = item.getString("publisher");
            String price = item.getString("price");
            String page = item.getString("page");
            String library = item.getString("lib_name");
            String isbn = item.getString("isbn");
            String image = item.getString("image");
            String author = item.getString("author");

            BookRequestDto requestDto = BookRequestDto.builder()
                    .title(title)
                    .publisher(publisher)
                    .price(price)
                    .page(page)
                    .library(library)
                    .isbn(isbn)
                    .image(image)
                    .author(author)
                    .build();

            Book newBook = requestDto.toEntity(Status.Book.ALIVE);

            bookRepository.save(newBook);
            i += 1;

            rd.close();
            conn.disconnect();
            System.out.println(sb.toString());
        }
        return sb.toString();
    }
}
