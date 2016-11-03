package br.com.senac.caiodiasaula2.geekquizdarkside;

import android.content.Intent;
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
                lg.doInBackground();
                lg.execute();

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

            try{
                 Usuario = URLEncoder.encode(Email.getText().toString(), "UTF-8");
                 SenhaUsuario = URLEncoder.encode(Senha.getText().toString(), "UTF-8");
            }catch (java.io.UnsupportedEncodingException e) {

            }

        }

        protected String doInBackground(Void... params) {
            try
            {
                URL url = new URL("http://darkdev.jelasticlw.com.br/srv/rest/Usuario/Login");
                HttpURLConnection con =
                        (HttpURLConnection) url.openConnection();
                con.setDoOutput(true);

                try{
                    OutputStreamWriter out =
                            new OutputStreamWriter(con.getOutputStream());

                    out.write("usuario=" + Usuario);
                    out.write("senha=" + SenhaUsuario);
                    out.close();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

                InputStream in = con.getInputStream();

                BufferedReader streamReader = new BufferedReader(
                        new InputStreamReader(in, "UTF-8"));

                StringBuilder responseStrBuilder = new StringBuilder();
                String inputStr;

                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);

                String result = responseStrBuilder.toString();

                try{

                    JSONArray jsonArray = new JSONArray(result);
                    if (jsonArray.length() == 3)
                    {
                        Intent intent = new Intent(Login.this, EventoActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    }else{

                    }
                }catch (Exception e)
                {
                    Toast toast = Toast.makeText(Login.this,
                            "Sua autenticaçao falhou. Certifique-se que seu usuário e senha estejam corretos"
                            , Toast.LENGTH_LONG);
                    toast.show();
                }

            }catch (java.net.MalformedURLException e){

            }catch (java.io.IOException e) {

            }

            return null;

        }
    }


}