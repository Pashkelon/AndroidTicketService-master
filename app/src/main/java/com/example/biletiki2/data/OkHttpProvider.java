package com.example.biletiki2.data;

import com.example.biletiki2.data.adminDto.getEvents.SearchEventListRequestDto;
import com.example.biletiki2.data.adminDto.getEvents.SearchEventListResponseDto;
import com.example.biletiki2.data.dto.auth.AuthRequestLoginDto;
import com.example.biletiki2.data.dto.auth.AuthRequestRegDto;
import com.example.biletiki2.data.dto.auth.AuthResponseLoginDto;
import com.example.biletiki2.data.dto.ErrorResponseDto;
import com.example.biletiki2.data.dto.Event;
import com.example.biletiki2.data.dto.EventListDto;
import com.example.biletiki2.data.dto.EventListRequestDto;
import com.example.biletiki2.data.dto.HallBookingDto;
import com.example.biletiki2.data.dto.ListLockedSeats;
import com.example.biletiki2.data.dto.LockedSeats;
import com.example.biletiki2.data.dto.TmpBookingNumberDto;
import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpProvider {
    public static final OkHttpProvider instance = new OkHttpProvider();
    private Gson gson;
    private OkHttpClient client;
    private MediaType JSON;
    public static final String SERVER_ERROR = "Server error! Call to support!";
    public static final String AUTHORIZATION = "Authorization";
    private static final String BASE_URL = "https://ticket-service.herokuapp.com";
    private static final String ACCEPT = "Accept";
    private int page = 1;

    public void plusPage() {
        page++;
    }

    public void minusPage(){
        page--;
    }

    public int getPage(){
        return page;
    }

    public void setPage(int p){
        this.page = p;
    }

    public static OkHttpProvider getInstance() {
        return instance;
    }

    private OkHttpProvider() {
        JSON = MediaType.get("application/json; charset=utf-8");

        gson = new Gson();
        client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    public void registration(String email, String firstName, String gender, String lastName,
                             String password, String phoneNumber) throws Exception {

        AuthRequestRegDto requestRegDto = new AuthRequestRegDto(email, firstName, gender,
                lastName, password, phoneNumber);
        String requestJson = gson.toJson(requestRegDto);

        RequestBody requestBody = RequestBody.create(JSON, requestJson);

        Request request = new Request.Builder()
                .url(BASE_URL + "/registration")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();

        if (response.code() == 401 || response.code() == 403 || response.code() == 404 || response.code() == 500) {
            ErrorResponseDto error = getErrorResponseDto(responseJson);
            throw new Exception(error.getMessage());
        } else if (!response.isSuccessful()) {
            throw new Exception(SERVER_ERROR);
        }
    }


    public String login(String email, String password) throws Exception {
        AuthRequestLoginDto requestLoginDto = new AuthRequestLoginDto(email, password);
        String requestJson = gson.toJson(requestLoginDto);

        RequestBody requestBody = RequestBody.create(JSON, requestJson);

        Request request = new Request.Builder()
                .url(BASE_URL + "/login")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();

        if (response.isSuccessful()) {
            AuthResponseLoginDto responseLoginDto = gson.fromJson(responseJson, AuthResponseLoginDto.class);
            return responseLoginDto.getToken();
        } else if (response.code() == 401 || response.code() == 403 || response.code() == 404 || response.code() == 500) {
            ErrorResponseDto error = getErrorResponseDto(responseJson);
            throw new Exception(error.getMessage());
        } else {
            throw new Exception(SERVER_ERROR);
        }
    }

    public Event getEventInfo(long id) throws Exception {//row click
        Request request = new Request.Builder()
                .url(BASE_URL + "/events/" + id)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();

        if (response.isSuccessful()) {
            return gson.fromJson(responseJson, Event.class);
        } else if (response.code() == 401 || response.code() == 403 || response.code() == 404) {
            ErrorResponseDto error = getErrorResponseDto(responseJson);
            throw new Exception(error.getMessage());
        } else {
            throw new Exception(SERVER_ERROR);
        }
    }

    public void deleteTemporaryBookingSeats(String tmpBookingNumber) throws Exception {

        Request request = new Request.Builder()
                .url(BASE_URL + "/hall/booking/" + tmpBookingNumber)
                .delete()
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();

        if (response.code() == 401 || response.code() == 403) {
            ErrorResponseDto error = getErrorResponseDto(responseJson);
            throw new Exception(error.getMessage());
        } else if (!response.isSuccessful()) {
            throw new Exception(SERVER_ERROR);
        }
    }


    public String getTmpBookingNumber(long id, List<LockedSeats> list) throws Exception {
        HallBookingDto hallBookingDto = new HallBookingDto(id, list);
        String requestJson = gson.toJson(hallBookingDto);

        RequestBody requestBody = RequestBody.create(JSON, requestJson);

        Request request = new Request.Builder()
                .url(BASE_URL + "/hall/booking")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();

        if (response.isSuccessful()) {
            TmpBookingNumberDto bookingNumberDto = gson.fromJson(responseJson, TmpBookingNumberDto.class);
            return bookingNumberDto.getTmpBookingNumber();
        } else if (response.code() == 401 || response.code() == 403 || response.code() == 404) {
            ErrorResponseDto error = getErrorResponseDto(responseJson);
            throw new Exception(error.getMessage());
        } else {
            throw new Exception(SERVER_ERROR);
        }
    }

    public List<Event> getEvents(long dateFrom, long dateTo, String type)
            throws Exception {

        EventListRequestDto listRequestDto = new EventListRequestDto(dateFrom, dateTo, type);
        String requestJson = gson.toJson(listRequestDto);

        RequestBody requestBody = RequestBody.create(JSON, requestJson);

        Request request = new Request.Builder()
                .url(BASE_URL + "/events?page="+page+"&page-size=5")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();

        if (response.isSuccessful()) {

            EventListDto eventListDto = gson.fromJson(responseJson, EventListDto.class);
            return eventListDto.getEvents();
        } else if (response.code() == 401 || response.code() == 403 || response.code() == 404) {
            ErrorResponseDto error = getErrorResponseDto(responseJson);
            throw new Exception(error.getMessage());
        } else {
            throw new Exception(SERVER_ERROR);
        }
    }


    public ListLockedSeats listLockedSeats(long id) throws Exception {
        Request request = new Request.Builder()
                .url(BASE_URL + "/hall/" + id + "/" + false)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();

        if (response.isSuccessful()) {
            ListLockedSeats listLockedSeats = gson.fromJson(responseJson, ListLockedSeats.class);
            return listLockedSeats;
        } else if (response.code() == 401 || response.code() == 403 || response.code() == 404) {
            ErrorResponseDto error = getErrorResponseDto(responseJson);
            throw new Exception(error.getMessage());
        } else {
            throw new Exception(SERVER_ERROR);
        }
    }


    private ErrorResponseDto getErrorResponseDto(String responseJson) {
        return gson.fromJson(responseJson, ErrorResponseDto.class);
    }
}
