package com.thirdpartyapi.webclientcallapi.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.thirdpartyapi.webclientcallapi.entity.Employee;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import okhttp3.*;

import java.io.IOException;

@RestController
public class AboutController {

    @Autowired
    private WebClient.Builder webClient;

    private final OkHttpClient client = new OkHttpClient();

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    //3rd party api call using rest template
    @GetMapping("/resttemplate")
    public String m1()
    {

        //Rest template
        RestTemplate restTemplate = new RestTemplate();
        String endPoint = "http://localhost:8080/home1";
        ResponseEntity<String> data = restTemplate.getForEntity(endPoint,String.class);
        if(data.getStatusCodeValue()==200)
        {
            String s=data.getBody();
            return s;
        }
        return "contact";
    }

    //3rd party api call using webclient
    @GetMapping("/webClient")
    public String m2()
    {

        //web client
       String s1 = webClient.build()
                            .get()
                            .uri("http://localhost:8080/home2")
                            .retrieve()
                            .bodyToMono(String.class)
                             .block();
       return s1;
    }

    // 3rd party api call using okHttp
    @GetMapping("/okHttp")
    public String makeGetRequest() throws IOException {

            String apiUrl = "http://localhost:8080/home3";

            Request request = new Request.Builder().url(apiUrl).build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    return response.body().string();
                } else {
                    throw new IOException("Unexpected response: " + response.code());
                }
            }
    }

    @PostMapping("/post")
    public String whenSendPostRequest_thenCorrect()
            throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("username", "test")
                .add("password", "test")
                .build();

        String BASE_URL = "http://localhost:8080/postData";

        Request request = new Request.Builder()
                .url(BASE_URL)
                .post(formBody)
                .build();


        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected HTTP code " + response);
            }

            // Handle response if needed
            return response.body().string(); // Return the response from /postData endpoint
        }

    }

    @PostMapping("/postOne")
    public String whenSendPostRequest()
            throws IOException {

        Employee employee= new Employee("Vedant","123");

        // Convert Employee object to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(employee);

        // Specify JSON media type
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        // Create request body
        RequestBody requestBody = RequestBody.create(jsonBody, JSON);

        // Specify the endpoint URL
        String BASE_URL = "http://localhost:8080/postData2";

        // Build the request
        Request request = new Request.Builder()
                .url(BASE_URL)
                .post(requestBody)
                .build();

        // Execute the request and handle the response
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected HTTP code " + response);
            }

            // Handle response if needed
            return response.body().string(); // Return the response from /postData2 endpoint

        }

    }

}
