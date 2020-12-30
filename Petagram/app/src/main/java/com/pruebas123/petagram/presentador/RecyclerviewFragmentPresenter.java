package com.pruebas123.petagram.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pruebas123.petagram.db.ConstructorMascotas;
import com.pruebas123.petagram.fragmnet.IRecyclerviewFragmentView;
import com.pruebas123.petagram.pojo.Mascota;
import com.pruebas123.petagram.restApi.EndpointsApi;
import com.pruebas123.petagram.restApi.adapter.RestApiAdapter;
import com.pruebas123.petagram.restApi.model.MascotaResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerviewFragmentPresenter implements IRecyclerviewFragmentPresenter {

    private IRecyclerviewFragmentView iRecyclerviewFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public RecyclerviewFragmentPresenter(IRecyclerviewFragmentView iRecyclerviewFragmentView, Context context) {
        this.iRecyclerviewFragmentView = iRecyclerviewFragmentView;
        this.context = context;
        //obtenerMascotasBaseDatos();
        obtenerMediosRecientes();
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        iRecyclerviewFragmentView.inicializarAdaptadorRV(iRecyclerviewFragmentView.crearAdaptador(mascotas));
        iRecyclerviewFragmentView.generarLinerLaoutVertical();
    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMedia();

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Algo paso con la conexion, Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("Fallo la conexion", t.toString());
            }
        });

    }
}
