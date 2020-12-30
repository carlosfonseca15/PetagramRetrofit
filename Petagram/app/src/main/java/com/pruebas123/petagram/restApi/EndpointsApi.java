package com.pruebas123.petagram.restApi;

import com.pruebas123.petagram.restApi.model.MascotaResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia();
}
