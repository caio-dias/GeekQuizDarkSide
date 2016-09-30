package br.com.senac.caiodiasaula2.geekquizdarkside;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MeuGrupo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_grupo);
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
            Intent intent = new Intent(MeuGrupo.this, EventoActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.ListaGrupos) {
            Intent intent = new Intent(MeuGrupo.this, ListagemGrupos.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.CriarGrupo) {
            Intent intent = new Intent(MeuGrupo.this, NovoGrupo.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.MeuGrupo) {
            Intent intent = new Intent(MeuGrupo.this, MeuGrupo.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.SobreNos) {
            Intent intent = new Intent(MeuGrupo.this, SobreNos.class);
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
