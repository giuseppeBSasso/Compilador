package app.br.v1.compilador.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import app.br.v1.compilador.model.Linguagem;

import app.br.v1.compilador.R;

import static app.br.v1.compilador.model.Linguagem.DELIMITADOR;

public class AnalisadorActivity extends AppCompatActivity {

    Button btnVoltar;
    Button btnSair;
    TextView textLog;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisador);

        textLog  = (TextView) findViewById(R.id.textLog);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnSair = (Button) findViewById(R.id.btnSair);;

           //    textLog.setTextColor(Color.parseColor("#2F99C8"));


        //  Chama método para incrementar o scroll ao TextView
        textLog.setMovementMethod(new ScrollingMovementMethod());

        textLog.setText(MainActivity.str);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AnalisadorActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}