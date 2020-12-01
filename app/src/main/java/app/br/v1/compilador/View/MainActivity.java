package app.br.v1.compilador.View;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import app.br.v1.compilador.R;
import app.br.v1.compilador.controller.Scanner;


public class MainActivity extends AppCompatActivity {

    public static String str;
    boolean isFormularioOk;
    EditText editTextTextMultiLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTextMultiLine = (EditText) findViewById(R.id.editTextTextMultiLine);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                if (isFormularioOk = validarCampos()) {

                    str = new Scanner().generateLog(editTextTextMultiLine.getText().toString());

                    Intent intent = new Intent(MainActivity.this, AnalisadorActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        });
    }

    private boolean validarCampos() {

        boolean retorno = true;

        //Checando se algo foi digitado ou esta em branco
        if (TextUtils.isEmpty(editTextTextMultiLine.getText().toString())) {
            editTextTextMultiLine.setError("Nenhum código informado!");
            editTextTextMultiLine.requestFocus();
            retorno = false;
        }

        return retorno;
    }
}