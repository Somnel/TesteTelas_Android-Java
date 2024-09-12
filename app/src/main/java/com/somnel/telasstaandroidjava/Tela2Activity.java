package com.somnel.telasstaandroidjava;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Tela2Activity extends AppCompatActivity {

    private TextView textView;
    private View view;
    private String backgroundColor;
    private SharedPreferences _preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);
        init();
    }

    private void init() {
        view = this.findViewById(R.id.tela2View);

        textView = (TextView) findViewById(R.id.tela2TextView);
        textView.setText("0");

        // Intent
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            // Valor
            textView.setText(extras.getString(getString(R.string.valorAtual)));
        }

        // Cor
        _preferences = this.getSharedPreferences(
                getString(R.string.preferColor), Context.MODE_PRIVATE
        );
        backgroundColor = _preferences.getString(getString(R.string.preferColor), "");

        if(backgroundColor.equals(getString(R.string.azul))) setBackgroundColor(getString(R.string.azul), getResources().getColor(R.color.azul));
        else if(backgroundColor.equals(getString(R.string.vermelho))) setBackgroundColor(getString(R.string.vermelho), getResources().getColor(R.color.vermelho));
    }

    private void setBackgroundColor(String nomeCor, int hexCor) {
        if(!nomeCor.isEmpty()) {
            backgroundColor = nomeCor;
            view.setBackgroundColor(hexCor);
        }
    }


    public void setViewAzul(View v) {
        setBackgroundColor(getString(R.string.azul), getResources().getColor(R.color.azul));
    }
    public void setViewVermelho(View v) {
        setBackgroundColor(getString(R.string.vermelho), getResources().getColor(R.color.vermelho));
    }

    public void adicionarCont(View v) {
        String aux = textView.getText().toString();
        int result = Integer.parseInt(aux) + 1;
        textView.setText(String.format("%d", result));
    }
    public void removerCont(View v) {
        String aux = textView.getText().toString();
        int result = Integer.parseInt(aux) - 1;
        textView.setText(String.format("%d", result));
    }




    private void redirect(Class tela) {
        // Salva valor atual
        Intent in = new Intent(this, tela);
        in.putExtra(getString(R.string.valorAtual), textView.getText().toString());

        // Salva cor atual
        SharedPreferences.Editor editor = _preferences.edit();
        editor.putString(getString(R.string.preferColor), backgroundColor);
        editor.apply();

        startActivity(in);
        finish();
    }

    public void tela2Voltar(View v) {
        redirect(tela1.class);
    }

    public void tela2Inicio(View v) {
        redirect(MainActivity.class);
    }
}