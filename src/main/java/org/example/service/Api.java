package org.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Api {
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public List<JsonNode> getUsers () throws IOException {
        final String URL = "https://jsonplaceholder.typicode.com/users";

        List<JsonNode> result = new ArrayList<>();

        Request request = new Request.Builder()
                .url(URL)
                .build();

        Response response = client.newCall(request).execute();
        String resultBody = response.body().string();

        JsonNode jsonResult = mapper.readTree(resultBody);

        for (JsonNode user : jsonResult) {
            result.add(user);
        }
        return result;
    }

    public JsonNode getUser () throws IOException {
        List<JsonNode> users = getUsers();

        return users.getFirst();
    }

}
