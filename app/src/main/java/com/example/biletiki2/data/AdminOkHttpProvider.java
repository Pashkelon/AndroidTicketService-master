package com.example.biletiki2.data;

import com.example.biletiki2.data.adminDto.AdminEvent;
import com.example.biletiki2.data.adminDto.getEvents.SearchEventListRequestDto;
import com.example.biletiki2.data.adminDto.getEvents.SearchEventListResponseDto;
import com.example.biletiki2.data.dto.ErrorResponseDto;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AdminOkHttpProvider {
    public static final AdminOkHttpProvider instance = new AdminOkHttpProvider();
    private Gson gson;
    private OkHttpClient client;
    private MediaType JSON;
    public static final String SERVER_ERROR = "Server error! Call to support!";
    public static final String AUTHORIZATION = "Authorization";
    private static final String BASE_URL = "https://ticket-service.herokuapp.com";

    public static AdminOkHttpProvider getInstance() {
        return instance;
    }

    private AdminOkHttpProvider() {
        JSON = MediaType.get("application/json; charset=utf-8");

        gson = new Gson();
        client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    public AdminEvent addEvent(AdminEvent event) throws Exception {
        String requestJson = gson.toJson(event);
        RequestBody requestBody = RequestBody.create(JSON, requestJson);

        Request request = new Request.Builder()
                .url(BASE_URL + "/admin/events")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();
        if (response.isSuccessful()){
            return gson.fromJson(responseJson,AdminEvent.class);
        }else if(response.code() == 401 || response.code() == 403 || response.code() == 404){
            ErrorResponseDto error = getErrorResponseDto(responseJson);
            throw new Exception(error.getMessage());
        }else {
            throw new Exception(SERVER_ERROR);
        }
    }

    public AdminEvent updateEvent(AdminEvent event) throws Exception{
        String requestJson = gson.toJson(event);
        RequestBody requestBody = RequestBody.create(JSON, requestJson);

        Request request = new Request.Builder()
                .url(BASE_URL + "/admin/events/" + event.getEventId())
                .put(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();
        if (response.isSuccessful()){
            return gson.fromJson(responseJson,AdminEvent.class);
        }else if(response.code() == 401 || response.code() == 403 || response.code() == 404){
            ErrorResponseDto error = getErrorResponseDto(responseJson);
            throw new Exception(error.getMessage());
        }else {
            throw new Exception(SERVER_ERROR);
        }
    }

    public AdminEvent getEventInfo(int eventId) throws Exception {
        Request request = new Request.Builder()
                .url(BASE_URL + "/admin/events/" + eventId )
                .get()
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();

        if(response.isSuccessful()){
            return gson.fromJson(responseJson,AdminEvent.class);
        }else if(response.code() == 401 || response.code() == 403 || response.code() == 404){
            ErrorResponseDto error = getErrorResponseDto(responseJson);
            throw new Exception(error.getMessage());
        }else{
            throw new Exception(SERVER_ERROR);
        }
    }

    public List<AdminEvent> getEvents(long dateFrom, long dateTo, int hall) throws Exception {
        SearchEventListRequestDto listRequestDto = new SearchEventListRequestDto(dateFrom,
                dateTo,hall);
        String requestJson = gson.toJson(listRequestDto);

        RequestBody requestBody = RequestBody.create(JSON, requestJson);

        Request request = new Request.Builder()
                .url(BASE_URL + "/admin/events/search?page=1&page-size=10")
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();

        if(response.isSuccessful()){
            SearchEventListResponseDto eventListResponseDto = gson.fromJson(responseJson,
                    SearchEventListResponseDto.class);
            return eventListResponseDto.getEvents();
        }else if(response.code() == 401 || response.code() == 403 || response.code() == 404){
            ErrorResponseDto error = getErrorResponseDto(responseJson);
            throw new Exception(error.getMessage());
        }else{
            throw new Exception(SERVER_ERROR);
        }
    }

    //todo  not LONG!!
    public String cancelEvent(int eventId, String cause) throws Exception {
        Request request = new Request.Builder()
                .url(BASE_URL + "/admin/events/" + eventId)
                .delete()
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();
        if(response.isSuccessful()){
            return cause;//todo .i think we must thinking about this shit
        }else if(response.code() == 204 || response.code() == 401 || response.code() == 403){
            ErrorResponseDto error = getErrorResponseDto(responseJson);
            throw new Exception(error.getMessage());
        }else {
            throw new Exception(SERVER_ERROR);
        }
    }

    private ErrorResponseDto getErrorResponseDto(String responseJson) {
        return gson.fromJson(responseJson, ErrorResponseDto.class);
    }
}
