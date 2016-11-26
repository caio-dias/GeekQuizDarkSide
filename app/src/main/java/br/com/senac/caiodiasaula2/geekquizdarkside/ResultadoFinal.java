package br.com.senac.caiodiasaula2.geekquizdarkside;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
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

        addItem("1 - ", "Caio Dias", "Acertos: 2", "Tempo: 3 minutos");
        addItem("2 - ", "Caio Silva", "Acertos: 1", "Tempo: 2 minutos");
        addItem("3 - ", "Caio ", "Acertos: 0", "Tempo: 1 minutos");
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
}
