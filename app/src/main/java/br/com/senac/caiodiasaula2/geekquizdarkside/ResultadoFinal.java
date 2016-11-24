package br.com.senac.caiodiasaula2.geekquizdarkside;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ResultadoFinal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_final);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
