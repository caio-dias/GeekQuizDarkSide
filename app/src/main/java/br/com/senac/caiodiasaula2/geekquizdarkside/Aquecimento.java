package br.com.senac.caiodiasaula2.geekquizdarkside;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;

import br.com.pi.pi4.GroupSelectionActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Aquecimento extends AppCompatActivity {
    AguardaJogoIniciar ag;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aquecimento);

        btn = (Button) findViewById(R.id.btnComecar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ag.doInBackground();
            }
        });



    }

    public class AguardaJogoIniciar extends AsyncTask<Void, Void, String>
    {

        public void executa(){
            doInBackground();
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                Toast.makeText(
                        Aquecimento.this,
                        "Seu jogo começará em 40 segundos",
                        Toast.LENGTH_LONG
                ).show();

                SystemClock.sleep(8000);

                Intent intent = new Intent(Aquecimento.this, Jogo.class);
                startActivity(intent);


            }catch (Exception e){

            }

            return null;
        }


    }
}
