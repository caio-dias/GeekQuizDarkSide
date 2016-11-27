package br.com.senac.caiodiasaula2.geekquizdarkside;

import org.json.JSONArray;

import java.util.List;

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

    //@GET("Perguntas/codEvento/{idEvento}")
    //Call<DadosQuestao> getPergunta(@Path("idEvento") String idEvento);

    @GET("Perguntas/codEvento/{codEvento}")
    Call<List<QuestoesEvento>>JsonAlternativa(@Path("codEvento") String codEvento);

    @GET("Perguntas/codEvento/{codEvento}/{codQuestao}")
    Call<List<Questoes>>JsonAlternativaQUESTOES(@Path("codEvento") String codEvento, @Path("codQuestao") String codQuestao);

    @GET("Perguntas/Insere/{codQuestao}/{codAlternativa}/{codGrupo}/{textoResp}/{correta}")
    Call<Void>InsereResposta(@Path("codQuestao") String codQuestao, @Path("codAlternativa") String codAlternativa,
                             @Path("codGrupo") String codGrupo,
                             @Path("textoResp") String textoResp, @Path("correta") String correta
                             );




}
