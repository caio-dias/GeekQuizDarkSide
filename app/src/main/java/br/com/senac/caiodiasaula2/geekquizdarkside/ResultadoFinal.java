package br.com.senac.caiodiasaula2.geekquizdarkside;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ResultadoFinal extends AppCompatActivity {


    private ViewGroup placar;
    MediaPlayer mp;

    @Override
    protected void onStop() {
        super.onStop();
        mp.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mp.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_final);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mp = MediaPlayer.create(this, R.raw.somfinal);
        mp.start();

        placar = (ViewGroup) findViewById(R.id.container_placar);

        getPlacar gp = new getPlacar();
        gp.execute();
    }

    private void addItem(String colocacao, String nome, String acertos, String tempo) {
        CardView cardView = (CardView) LayoutInflater.from(this).inflate(R.layout.card_placar, placar, false);
        TextView colocado = (TextView) cardView.findViewById(R.id.colocacao);
        TextView nomeJogador = (TextView) cardView.findViewById(R.id.nomeJogador);
        TextView acerto = (TextView) cardView.findViewById(R.id.acertos);
        TextView tempoJogo = (TextView) cardView.findViewById(R.id.tempo);

        colocado.setText(colocacao);
        nomeJogador.setText(nome);
        acerto.setText(acertos);
        tempoJogo.setText(tempo);

        placar.addView(cardView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Evento) {
            Intent intent = new Intent(ResultadoFinal.this, EventoActivity.class);
            startActivity(intent);
            return true;
        }  else if (id == R.id.SobreNos) {
            Intent intent = new Intent(ResultadoFinal.this, SobreNos.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.Sair) {
            SharedPreferences preferences2 = getSharedPreferences("Info", MODE_PRIVATE);
            SharedPreferences.Editor editor2 = preferences2.edit();
            editor2.putString("nome", null);
            editor2.putString("codParticipante", null);
            editor2.putString("Status", null);
            editor2.putString("login", null);
            editor2.putString("senha", null);
            editor2.apply();

            Intent intent = new Intent(ResultadoFinal.this, Login.class);
            startActivity(intent);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class getPlacar extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {

            SharedPreferences pref = getSharedPreferences("info", MODE_PRIVATE);
            final String CodEvento = pref.getString("codEvento", "");

            Retrofit retrofit = Rest.getInstance().get();
            final RestEndPointDarkSide service = retrofit.create(RestEndPointDarkSide.class);
            String Opcao = "0";

            final Call<List<Placar>> callPlacar = service.GetPlacar(CodEvento);

            callPlacar.enqueue(new Callback<List<Placar>>() {
                @Override
                public void onResponse(Call<List<Placar>> call, Response<List<Placar>> response) {
                    if (response.isSuccessful()) {
                        List<Placar> placar = response.body();

                        for(int i = 0; i < placar.size(); i++)
                        {
                            addItem( (i+1) + " - ",  placar.get(i).getNmGrupo().toString(), "Acertos: " + placar.get(i).getTotalRespostaCerta().toString(), "Tempo: "+ placar.get(i).getTempoRespostaSec().toString() +" segundos");
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Placar>> call, Throwable t) {

                }
            });



            return null;
        }
    }
}
