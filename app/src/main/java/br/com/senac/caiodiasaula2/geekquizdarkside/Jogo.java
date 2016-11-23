package br.com.senac.caiodiasaula2.geekquizdarkside;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Jogo extends AppCompatActivity {
    private ViewGroup alternativas;
    private TextView titulo_pergunta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        alternativas = (ViewGroup) findViewById(R.id.container_alternativas);
        titulo_pergunta = (TextView) findViewById(R.id.titulo_pergunta);

        titulo_pergunta.setText("Titulo da pergunta");

        addItem("Alternativa 1");
        addItem("Alternativa 2");
        addItem("Alternativa 3");
        addItem("Alternativa 4");
    }

    private void addItem(String textoDaPergunta) {
        CardView cardView = (CardView) LayoutInflater.from(this).inflate(R.layout.card_message, alternativas, false);
        TextView alternativa = (TextView) cardView.findViewById(R.id.alternativa);
        alternativa.setText(textoDaPergunta);
        alternativas.addView(cardView);
    }

}
