package br.com.senac.caiodiasaula2.geekquizdarkside;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Login extends AppCompatActivity  {
    public Button botaoLogin;
    public TextView Email;
    public TextView Senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        botaoLogin = (Button) findViewById(R.id.botaoLogin);

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            LoginUsuario lg  = new LoginUsuario();
                lg.onPreExecute();
                lg.doInBackground();
            }
        });

    }

    public class LoginUsuario extends AsyncTask<Void, Void, String> {

        public String Usuario;
        public String SenhaUsuario;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Email = (TextView)findViewById(R.id.email);
            Senha = (TextView)findViewById(R.id.senha);

            Usuario = Email.getText().toString();
            SenhaUsuario = Senha.getText().toString();

        }

        protected String doInBackground(Void... params) {
            try
            {
                Retrofit retrofit = Rest.getInstance().get();
                RestEndPointDarkSide service = retrofit.create(RestEndPointDarkSide.class);
                Call<UsuarioStatus> call;
                final LoginExtract login = new LoginExtract();
                login.setUsuario(Usuario);
                login.setSenha(SenhaUsuario);
                call = service.getUser(login);

                call.enqueue(new Callback<UsuarioStatus>() {
                    @Override
                    public void onResponse(Call<UsuarioStatus> call, Response<UsuarioStatus> response) {
                        if(response.isSuccessful()){
                            final UsuarioStatus us = response.body();

                            SharedPreferences pref = getSharedPreferences("info", MODE_PRIVATE);

                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("codParticipante", us.getCodParticipante().toString());
                            editor.apply();

                            Intent intent = new Intent(Login.this, EventoActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.right_in, R.anim.left_out);
                        }else{
                            Toast
                                    .makeText(
                                            Login.this,
                                            "Nao foi possivel logar",
                                            Toast.LENGTH_LONG)
                                    .show();

                        }
                    }

                    @Override
                    public void onFailure(Call<UsuarioStatus> call, Throwable t) {

                    }
                });


            }catch(Exception e) {
                e.printStackTrace();
            }

            return null;

        }
    }


}