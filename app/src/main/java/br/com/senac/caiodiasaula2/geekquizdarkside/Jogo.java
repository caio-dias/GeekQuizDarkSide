package br.com.senac.caiodiasaula2.geekquizdarkside;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Jogo extends AppCompatActivity {
    private ViewGroup alternativas;
    private TextView titulo_pergunta;
    private SharedPreferences prefQuestoes;
    private SharedPreferences.Editor editorQuestoes;

    private String CodEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MediaPlayer mp = MediaPlayer.create(this, R.raw.musicafundo);
        mp.stop();

        final SharedPreferences pref = getSharedPreferences("info", MODE_PRIVATE);
        alternativas = (ViewGroup) findViewById(R.id.container_alternativas);

        prefQuestoes = getSharedPreferences("questoes", MODE_PRIVATE);
        editorQuestoes = prefQuestoes.edit();

        GetQuestoes gq = new GetQuestoes();
        gq.callQuestoes();

        int qtdQuestoes = prefQuestoes.getInt("QtdQuestoes", 0);




    }

    private void addItem(String textoDaPergunta, String idPergunta, String correta) {
        CardView cardView = (CardView) LayoutInflater.from(this).inflate(R.layout.card_message, alternativas, false);
        TextView alternativa = (TextView) cardView.findViewById(R.id.alternativa);
        TextView idPerguntaTexto = (TextView) cardView.findViewById(R.id.idAlternativa);
        TextView corretaTexto = (TextView) cardView.findViewById(R.id.correta);
        alternativa.setText(textoDaPergunta);
        idPerguntaTexto.setText(idPergunta);
        corretaTexto.setText(correta);
        alternativas.addView(cardView);
    }

    public class GetQuestoes extends AsyncTask<Void, Void, String>
    {
        public void callQuestoes()
        {
            doInBackground();
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                SharedPreferences pref = getSharedPreferences("info", MODE_PRIVATE);
                final String CodEvento = pref.getString("codEvento", "");

                Retrofit retrofit = Rest.getInstance().get();
                final RestEndPointDarkSide service = retrofit.create(RestEndPointDarkSide.class);
                final Call<List<QuestoesEvento>> callDa = service.JsonAlternativa(CodEvento);
                callDa.enqueue(new Callback<List<QuestoesEvento>>() {
                    @Override
                    public void onResponse(Call<List<QuestoesEvento>> call, Response<List<QuestoesEvento>> response) {
                        if (response.isSuccessful()) {
                            List<QuestoesEvento> questoes = response.body();
                            int QuestoesTotais =  questoes.size();

                            editorQuestoes.putInt("QtdQuestoes", QuestoesTotais);
                            editorQuestoes.apply();

                            for (int i = 0; i < questoes.size(); i++)
                            {
                                final Call<Questoes> callDaQuestoes = service.JsonAlternativaQUESTOES(CodEvento, questoes.get(i).getCodQuestao());
                                final int indice = i;
                                callDaQuestoes.enqueue(new Callback<Questoes>() {
                                    @Override
                                    public void onResponse(Call<Questoes> call, Response<Questoes> response) {
                                        if (response.isSuccessful()) {
                                            final Questoes questoesEvento = response.body();


                                                editorQuestoes.putString("Questao" + indice, questoesEvento.getTextoquestao().toString() + "#" +  questoesEvento.getTextoalternativa().toString()
                                                        + "#"+  questoesEvento.getCodalternativa().toString()
                                                        + "#"+ questoesEvento.getCorreta());
                                            editorQuestoes.apply();


                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Questoes> call, Throwable t) {
                                        Log.d("hh","k");
                                    }
                                });


                                //titulo_pergunta = (TextView) findViewById(R.id.titulo_pergunta);

                                //titulo_pergunta.setText(questoes.get(i).getTEXTOQUESTAO().toString());

                            }

                        } else {
                            Toast
                                    .makeText(
                                            Jogo.this,
                                            "Nao foi possivel logar",
                                            Toast.LENGTH_LONG)
                                    .show();

                        }
                    }
                    @Override
                    public void onFailure(Call<List<QuestoesEvento>> call, Throwable t) {
                        Log.d("hh","k");
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
