package com.example.biletiki2.data.orderControl;

import com.example.biletiki2.data.AdminOkHttpProvider;
import com.example.biletiki2.data.dto.ErrorResponseDto;
import com.example.biletiki2.data.orderControl.deleteSession.DeleteSession;
import com.example.biletiki2.data.orderControl.getHistory.ShoppingHistory;
import com.example.biletiki2.data.orderControl.getHistory.ShoppingHistoryResponse;
import com.example.biletiki2.data.orderControl.seatReservation.PlaceForAdmin;
import com.example.biletiki2.data.orderControl.seatReservation.ReservationRequest;
import com.example.biletiki2.data.orderControl.seatReservation.ReservationSeat;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OrderOkHttpProvider {
    public static final OrderOkHttpProvider instance = new OrderOkHttpProvider();
    private Gson gson;
    private OkHttpClient client;
    private MediaType JSON;
    public static final String SERVER_ERROR = "Server error! Call to support!";
    public static final String AUTHORIZATION = "Authorization";
    private static final String BASE_URL = "https://ticket-service.herokuapp.com";
    private static final String ACCEPT = "Accept";

    public static OrderOkHttpProvider getInstance() {
        return instance;
    }

    private OrderOkHttpProvider() {
        JSON = MediaType.get("application/json; charset=utf-8");

        gson = new Gson();
        client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    public List<ShoppingHistory> getHistory() throws Exception {
        Request request = new Request.Builder()
                .url(BASE_URL + "/orders/history")
                .get()
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();
        if(response.isSuccessful()){
            ShoppingHistoryResponse shoppingHistoryResponse = gson.fromJson(responseJson,
                                ShoppingHistoryResponse.class);
            return shoppingHistoryResponse.getShoppingHistory();
        }else if(response.code() == 401 || response.code() == 403 || response.code() == 404){
            ErrorResponseDto error = getErrorResponseDto(responseJson);
            throw new Exception(error.getMessage());
        }else {
            throw new Exception(SERVER_ERROR);
        }
    }

    public void deleteSession(int sessionId) throws Exception {
        DeleteSession deleteSession = new DeleteSession(sessionId);
        String requestJson  =  gson.toJson(deleteSession);
        RequestBody requestBody = RequestBody.create(JSON,requestJson);

        Request request = new Request.Builder()
                .url(BASE_URL + "/orders/reservation")
                .delete(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();
        if(response.isSuccessful()){
            SuccessResponse successResponse = gson.fromJson(responseJson, SuccessResponse.class);
          //  String res = successResponse.getMessage();
        }else if(response.code() == 204 || response.code() == 401 || response.code() == 403){
            ErrorResponseDto error = getErrorResponseDto(responseJson);
            throw new Exception(error.getMessage());
        }else {
            throw new Exception(SERVER_ERROR);
        }
    }
        //todo sessiO id must be changed in backend
    public List<PlaceForAdmin> seatReservation( int place, int row, int sessioId, String side)
                                                                            throws Exception {
        ReservationRequest reservationRequest = new ReservationRequest(place,row,sessioId,side);
        String requestJson = gson.toJson(reservationRequest);
        RequestBody requestBody = RequestBody.create(JSON, requestJson);

        Request request = new Request.Builder()
                .url(BASE_URL + "/orders/reservation")
                .put(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();
        if(response.isSuccessful()){
            ReservationSeat reservationSeat = gson.fromJson(responseJson, ReservationSeat.class);
            return reservationSeat.getList();
        }else if(response.code() == 401 || response.code() == 403 || response.code() == 404){
            ErrorResponseDto error = getErrorResponseDto(responseJson);
            throw new Exception(error.getMessage());
        }else{
            throw new Exception(SERVER_ERROR);
        }
    }

    public void daleteTicketFromReservation(int place, int row, int sessioId, String side)
            throws Exception {
        ReservationRequest reservationRequest = new ReservationRequest(place,row,sessioId,side);
        String requestJson = gson.toJson(reservationRequest);
        RequestBody requestBody = RequestBody.create(JSON, requestJson);

        Request request = new Request.Builder()
                .url(BASE_URL + "/orders/reservation/ticket")
                .delete(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();
        if(response.isSuccessful()){
            SuccessResponse successResponse = gson.fromJson(responseJson, SuccessResponse.class);
           // String res = successResponse.getMessage();
        }else if(response.code() == 204 || response.code() == 401 || response.code() == 403){
            ErrorResponseDto error = getErrorResponseDto(responseJson);
            throw new Exception(error.getMessage());
        }else{
            throw new Exception(SERVER_ERROR);
        }
    }

    private ErrorResponseDto getErrorResponseDto(String responseJson) {
        return gson.fromJson(responseJson, ErrorResponseDto.class);
    }
}
