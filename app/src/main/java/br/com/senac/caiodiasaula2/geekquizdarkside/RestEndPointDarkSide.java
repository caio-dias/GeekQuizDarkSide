package br.com.senac.caiodiasaula2.geekquizdarkside;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.Call;

/**
 * Created by joaopaulo on 03/11/16.
 */

public interface RestEndPointDarkSide {

    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @POST("Usuario/Login")
    Call<UsuarioStatus> getUser(@Body LoginExtract login);

    @GET("Evento/{eventoId}")
    Call<EventoStatus> getEvento(@Path("eventoId") String eventoId);

    @GET("Evento/{eventoId}")
    Call<EventoStatus> getEventoLiberado(@Path("eventoId") String eventoId);





}
