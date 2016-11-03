package br.com.senac.caiodiasaula2.geekquizdarkside;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EventoActivity extends AppCompatActivity {
    private TextView qrcode;
    private EditText evento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        qrcode = (TextView)findViewById(R.id.qrcode);
        evento = (EditText) findViewById(R.id.evento);


        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pede permisao para acessar a camera
                if (ContextCompat.checkSelfPermission(EventoActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(EventoActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    //inicia nova atividade
                    Intent intent = new Intent(EventoActivity.this, CameraActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
            }
        });

        Intent intent = getIntent();
        String resultadoQR = intent.getStringExtra("resultadoQRCODE");
        evento.setText(resultadoQR);



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
            Intent intent = new Intent(EventoActivity.this, EventoActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
            return true;
        } else if (id == R.id.ListaGrupos) {
            Intent intent = new Intent(EventoActivity.this, ListagemGrupos.class);
            startActivity(intent);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
            return true;
        } else if (id == R.id.CriarGrupo) {
            Intent intent = new Intent(EventoActivity.this, NovoGrupo.class);
            startActivity(intent);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
            return true;
        } else if (id == R.id.MeuGrupo) {
            Intent intent = new Intent(EventoActivity.this, MeuGrupo.class);
            startActivity(intent);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
            return true;
        } else if (id == R.id.SobreNos) {
            Intent intent = new Intent(EventoActivity.this, SobreNos.class);
            startActivity(intent);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
