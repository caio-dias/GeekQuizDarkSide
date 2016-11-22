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
    private ViewGroup mensagens;
    private TextView titulo_pergunta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mensagens = (ViewGroup) findViewById(R.id.container);
        titulo_pergunta = (TextView) findViewById(R.id.titulo_pergunta);

        titulo_pergunta.setText("Titulo da pergunta");

        addItem("Título 1", "Exemplo de mensagem.");
        addItem("Título 2", "Exemplo de mensagem.");
        addItem("Título 3", "Exemplo de mensagem.");
        addItem("Título 4", "Exemplo de mensagem.");
        addItem("Título 5", "Exemplo de mensagem.");
    }

    private void addItem(String textoDoTitulo, String textoDaMensagem) {
        CardView cardView = (CardView) LayoutInflater.from(this).inflate(R.layout.card_message, mensagens, false);
        TextView titulo = (TextView) cardView.findViewById(R.id.titulo);
        TextView mensagem = (TextView) cardView.findViewById(R.id.mensagem);
        titulo.setText(textoDoTitulo);
        mensagem.setText(textoDaMensagem);
        mensagens.addView(cardView);
    }

}
