package br.com.senac.caiodiasaula2.geekquizdarkside;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.design.widget.CoordinatorLayout;
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

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Jogo extends AppCompatActivity {
    private ViewGroup alternativas;
    private TextView titulo_pergunta;
    private SharedPreferences prefQuestoes;
    private SharedPreferences prefArrayQuestoes;
    private SharedPreferences.Editor editorQuestoes;
    private SharedPreferences.Editor editorArrayQuestoes;

    private String[] ArrayQuestoes;
    private String CodEvento;
    private Integer valorArray;

    private Toolbar mtoolbar;
    private CoordinatorLayout coordinatorLayout;
    private FloatingActionButton fab;

    MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onStop() {
        super.onStop();

        editorArrayQuestoes.clear();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);

        valorArray = 0;
        final SharedPreferences pref = getSharedPreferences("info", MODE_PRIVATE);
        alternativas = (ViewGroup) findViewById(R.id.container_alternativas);

        prefQuestoes = getSharedPreferences("questoes", MODE_PRIVATE);
        editorQuestoes = prefQuestoes.edit();

        prefArrayQuestoes = getSharedPreferences("ArrayQuestoes", MODE_PRIVATE);
        editorArrayQuestoes = prefArrayQuestoes.edit();

        String SharedArray = prefArrayQuestoes.getString("valorArray", "0");
        Integer arr = Integer.parseInt(SharedArray);

        editorArrayQuestoes.putString("valorArray", arr.toString());
        editorArrayQuestoes.apply();

        GetQuestoes gq = new GetQuestoes();
        gq.execute();

    }

    private void addItem(String textoDaPergunta, final String idPergunta, String correta,
                         final String codigoAlternativa) {
        CardView cardView = (CardView) LayoutInflater.from(this).inflate(R.layout.card_message, alternativas, false);
        final TextView alternativa = (TextView) cardView.findViewById(R.id.alternativa);
        final TextView idPerguntaTexto = (TextView) cardView.findViewById(R.id.idAlternativa);
        final TextView corretaTexto = (TextView) cardView.findViewById(R.id.correta);
        final TextView codAlternativa = (TextView) cardView.findViewById(R.id.codAlternativa);
        idPerguntaTexto.setText(idPergunta);
        codAlternativa.setText(codigoAlternativa);
        corretaTexto.setText(correta);
        alternativa.setText(textoDaPergunta);
        alternativa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer Analise = Integer.parseInt(corretaTexto.getText().toString().trim());

                if(Analise  == 1)
                {
                    mp = MediaPlayer.create(Jogo.this, R.raw.acertou);
                    mp.start();

                    Snackbar snack = Snackbar.make(coordinatorLayout, "Parece que voce está indo muito bem :) estamos preparando a próxima.",
                            Snackbar.LENGTH_LONG  );

                    snack.show();
                }else{
                    Snackbar snack = Snackbar.make(coordinatorLayout, "Preferimos não comentar sobre sua resposta :P",
                            Snackbar.LENGTH_LONG  );
                    snack.show();

                    mp = MediaPlayer.create(Jogo.this, R.raw.errou);
                    mp.start();
                }


                InsereResposta ins = new InsereResposta();
                ins.setCodAlternativa(codAlternativa.getText().toString());
                //ins.setCodGrupo(codGrupo);
                ins.setCodQuestao(idPerguntaTexto.getText().toString());
                ins.setCorreta(corretaTexto.getText().toString());

                ins.setTextoResp(alternativa.getText().toString());

                ins.doInBackground();

            }
        });
        idPerguntaTexto.setText(idPergunta);
        corretaTexto.setText(correta);
        alternativas.addView(cardView);
    }

    private void RequestQuestoes(){
        String SharedArray = prefArrayQuestoes.getString("valorArray", "0");
        Integer arr = Integer.parseInt(SharedArray);
        if(arr < ArrayQuestoes.length) {
            GetQuestoesPerguntas getQ = new GetQuestoesPerguntas();
            getQ.doInBackground();
            arr++;
            editorArrayQuestoes.putString("valorArray", arr.toString() );
            editorArrayQuestoes.apply();
        }else{
            editorArrayQuestoes.putString("valorArray", "0");
            editorArrayQuestoes.apply();

            Intent intent = new Intent(Jogo.this, ResultadoFinal.class);
            startActivity(intent);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    }

    private void MontaCards(){
        int OrdemPergunta = 0;
        int qtdQuestoes = prefQuestoes.getInt("QtdQuestoes", 0);
        String PerguntaSring;
        String Simbolo = "#";
        int ControleQuestao = 0;

        PerguntaSring = prefQuestoes.getString("Questao"+OrdemPergunta, "");
        String[] perguntas =  PerguntaSring.split(Simbolo);
        ControleQuestao = Integer.parseInt(perguntas[2].toString());
        titulo_pergunta = (TextView) findViewById(R.id.titulo_pergunta);
        titulo_pergunta.setText(perguntas[0].toString());

        try{
        if(qtdQuestoes > 0){

            while(ControleQuestao ==  Integer.parseInt(perguntas[2].toString()))
            {
                OrdemPergunta++;
                PerguntaSring = prefQuestoes.getString("Questao"+OrdemPergunta, "");
                perguntas =  PerguntaSring.split(Simbolo);

                addItem(perguntas[1].toString(), perguntas[2].toString(), perguntas[3].toString(),
                        perguntas[4].toString());

                ControleQuestao = Integer.parseInt(perguntas[2].toString());
                editorQuestoes.putInt("OrdemPergunta", OrdemPergunta);
                editorQuestoes.apply();
            }
        }
        }catch (Exception e){

        }


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

                            ArrayQuestoes = new String[QuestoesTotais];
                            for (int i = 0; i < questoes.size()-1; i++)
                            {
                                try {
                                    ArrayQuestoes[i] = questoes.get(i).getCodQuestao();
                                }catch (Exception e)
                                {
                                    e.printStackTrace();
                                }

                            }

                                RequestQuestoes();


                        } else {
                            Toast
                                    .makeText(
                                            Jogo.this,
                                            "Nao foi possivel criar as perguntas",
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

            return "done";
        }


    }

    public  class GetQuestoesPerguntas extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {

            String SharedArray = prefArrayQuestoes.getString("valorArray", "0");
            Integer arr = Integer.parseInt(SharedArray);

            SharedPreferences pref = getSharedPreferences("info", MODE_PRIVATE);
            final String CodEvento = pref.getString("codEvento", "");

            Retrofit retrofit = Rest.getInstance().get();
            final RestEndPointDarkSide service = retrofit.create(RestEndPointDarkSide.class);
            String Opcao = "0";
            try{
                Opcao = ArrayQuestoes[arr].toString();
                valorArray++;
            }catch (Exception e){
                editorArrayQuestoes.putString("valorArray", "0");
                editorArrayQuestoes.apply();

                Intent intent = new Intent(Jogo.this, ResultadoFinal.class);
                startActivity(intent);
            }

            final Call<List<Questoes>> callDaQuestoes = service.JsonAlternativaQUESTOES(CodEvento, Opcao);

            callDaQuestoes.enqueue(new Callback<List<Questoes>>() {
                @Override
                public void onResponse(Call<List<Questoes>> call, Response<List<Questoes>> response) {
                    if (response.isSuccessful()) {
                        List<Questoes> questoesEvento = response.body();

                        for (int i = 0; i < questoesEvento.size(); i++) {

                            addItem(questoesEvento.get(i).getTextoalternativa(),
                                    questoesEvento.get(i).getCodquestao(), questoesEvento.get(i).getCorreta(),
                                    questoesEvento.get(i).getCodalternativa());


                            titulo_pergunta = (TextView) findViewById(R.id.titulo_pergunta);
                            titulo_pergunta.setText(questoesEvento.get(i).getTextoquestao().toString());
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Questoes>> call, Throwable t) {
                    Log.d("hh","k");
                }
            });

            return null;
        }
    }

    public class InsereResposta extends AsyncTask<Void, Void, Void>{

        private String codQuestao;
        private String codAlternativa;
        private String codGrupo;
        private String tempo;
        private String textoResp;
        private String correta;

        private ProgressDialog pdia;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pdia = new ProgressDialog(Jogo.this);
            pdia.setMessage("Salvando sua resposta e criando nova pergunta :)");
            pdia.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try{
                SharedPreferences pref = getSharedPreferences("info", MODE_PRIVATE);
                final String CodEvento = pref.getString("codEvento", "");

                pref = getSharedPreferences("info", MODE_PRIVATE);
                String codParticipante = pref.getString("codParticipante", "");


                String codGrupo = "174";

            Retrofit retrofit = Rest.getInstance().get();
            final RestEndPointDarkSide service = retrofit.create(RestEndPointDarkSide.class);
            final Call<List<Grupo>> callGrupo = service.GetGrupo(CodEvento, codParticipante);

            callGrupo.enqueue(new Callback<List<Grupo>>() {
                @Override
                public void onResponse(Call<List<Grupo>> call, Response<List<Grupo>> response) {
                    if(response.isSuccessful())
                    {
                        List<Grupo> grupo = response.body();

                        for(int i =0; i < grupo.size(); i++){
                            final Call<Void> callDa = service.InsereResposta(getCodQuestao(), getCodAlternativa(),
                                    grupo.get(i).getCodgrupo(), getTextoResp(), getCorreta());
                            callDa.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if (response.isSuccessful()) {
                                        Intent intent = new Intent(Jogo.this, Jogo.class);
                                        startActivity(intent);
                                        //RequestQuestoes();
                                    } else {
                                        Toast
                                                .makeText(
                                                        Jogo.this,
                                                        "Nao foi possivel criar as perguntas",
                                                        Toast.LENGTH_LONG)
                                                .show();
                                    }
                                }
                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    Log.d("hh","k");
                                }
                            });
                        }


                    }
                }

                @Override
                public void onFailure(Call<List<Grupo>> call, Throwable t) {
                    Log.d("k","h");

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            pdia.dismiss();
        }

        public String getCodQuestao() {
            return codQuestao;
        }

        public void setCodQuestao(String codQuestao) {
            this.codQuestao = codQuestao;
        }

        public String getCodAlternativa() {
            return codAlternativa;
        }

        public void setCodAlternativa(String codAlternativa) {
            this.codAlternativa = codAlternativa;
        }

        public String getCodGrupo() {
            return codGrupo;
        }

        public void setCodGrupo(String codGrupo) {
            this.codGrupo = codGrupo;
        }

        public String getTempo() {
            return tempo;
        }

        public void setTempo(String tempo) {
            this.tempo = tempo;
        }

        public String getTextoResp() {
            return textoResp;
        }

        public void setTextoResp(String textoResp) {
            this.textoResp = textoResp;
        }

        public String getCorreta() {
            return correta;
        }

        public void setCorreta(String correta) {
            this.correta = correta;
        }
    }
}
