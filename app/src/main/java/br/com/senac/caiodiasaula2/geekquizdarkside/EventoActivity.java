package br.com.senac.caiodiasaula2.geekquizdarkside;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.pi.pi4.GroupSelectionActivity;
import br.com.pi.pi4.entity.Evento;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EventoActivity extends AppCompatActivity {
    private TextView qrcode;
    private EditText evento;
    private Button btnEntrarEvento;
    private TextView CodEvento;
    private Integer codUsuario;
    private EntraEvento ev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        qrcode = (TextView)findViewById(R.id.qrcode);
        evento = (EditText) findViewById(R.id.evento);
        btnEntrarEvento = (Button) findViewById(R.id.btnEntrarEvento);

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
                }
            }
        });


        btnEntrarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ev = new EntraEvento();
                ev.onPreExecute();
                ev.doInBackground();
                /*Intent i = new Intent(EventoActivity.this, GroupSelectionActivity.class);
                Bundle b = new Bundle();
                b.putString("evento", "7"); // segundo parametro é o identificador do evento (variavel que trata o identificador e transforma ele no codEvento)
                b.putString("participanteId", "258"); //segundo parametro é o id do participante que está logado
                b.putString("proximaTela", Aquecimento.class.getName());
                i.putExtras(b); //Put your id to your next Intent
                startActivity(i);*/
            }
        });

        Intent intent = getIntent();
        String resultadoQR = intent.getStringExtra("resultadoQRCODE");

        /*if (!resultadoQR.isEmpty()){
            qrcode = (TextView)findViewById(R.id.qrcode);
            qrcode.setText(resultadoQR);

        }*/

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
            return true;
        } else if (id == R.id.ListaGrupos) {
            Intent intent = new Intent(EventoActivity.this, ListagemGrupos.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.CriarGrupo) {
            Intent intent = new Intent(EventoActivity.this, NovoGrupo.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.MeuGrupo) {
            Intent intent = new Intent(EventoActivity.this, MeuGrupo.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.SobreNos) {
            Intent intent = new Intent(EventoActivity.this, SobreNos.class);
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public class EntraEvento extends AsyncTask<Void, Void, String> {

        public String idEvento;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            CodEvento = (TextView)findViewById(R.id.evento);
            idEvento = CodEvento.getText().toString().toUpperCase();
        }

        protected String doInBackground(Void... params) {
            try
            {
                Retrofit retrofit = Rest.getInstance().get();
                RestEndPointDarkSide service = retrofit.create(RestEndPointDarkSide.class);
                Call<EventoStatus> call;
                final EventoExtract evento = new EventoExtract();
                evento.setEventoId(idEvento);

                call = service.getEvento(evento.getEventoId());

                final SharedPreferences pref = getSharedPreferences("info", MODE_PRIVATE);
                final SharedPreferences.Editor editor = pref.edit();

                call.enqueue(new Callback<EventoStatus>() {
                    @Override
                    public void onResponse(Call<EventoStatus> call, Response<EventoStatus> response) {
                        if(response.isSuccessful()){
                            final EventoStatus us = response.body();
                            Intent i = new Intent(EventoActivity.this, GroupSelectionActivity.class);
                            Bundle b = new Bundle();
                            b.putString("evento", us.getIdEvento().toString()); // segundo parametro é o identificador do evento (variavel que trata o identificador e transforma ele no codEvento)
                            b.putString("participanteId", pref.getString("codParticipante", "")); //segundo parametro é o id do participante que está logado
                            b.putString("proximaTela", Aquecimento.class.getName());

                            editor.putString("codEvento", us.getIdEvento().toString());
                            editor.apply();

                            i.putExtras(b); //Put your id to your next Intent
                            startActivity(i);
                            overridePendingTransition(R.anim.right_in, R.anim.left_out);
                        }else{
                            Toast
                                    .makeText(
                                            EventoActivity.this,
                                            "Nao foi  possível encontrar este evento " + idEvento.toString(),
                                            Toast.LENGTH_LONG)
                                    .show();

                        }
                    }

                    @Override
                    public void onFailure(Call<EventoStatus> call, Throwable t) {

                    }
                });


            }catch(Exception e) {
                e.printStackTrace();
            }

            return null;

        }
    }
}
