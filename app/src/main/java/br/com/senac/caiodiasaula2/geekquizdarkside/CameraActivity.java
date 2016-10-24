package br.com.senac.caiodiasaula2.geekquizdarkside;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class CameraActivity extends AppCompatActivity {
    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //abrindo a camera
        scannerView = new ZXingScannerView(CameraActivity.this);
        //usando como layout o preview da camera
        setContentView(scannerView);


    }

    @Override
    protected void onResume() {
        super.onResume();
        //listener para verificar se ele le um qrcode
        scannerView.setResultHandler(new ZXingScannerView.ResultHandler() {
            @Override
            public void handleResult(Result result) {
                //mostra o que pegou no scanner em um toast
                Intent intent = new Intent(CameraActivity.this, EventoActivity.class);
                intent.putExtra("resultadoQRCODE", result.getText().toString());
                startActivity(intent);
                finish();
            }
        });
        scannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

}
