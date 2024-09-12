package com.somnel.telasstaandroidjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class tela1 extends AppCompatActivity {
    private TextView textView;
    private View view;
    private String backgroundColor;
    private SharedPreferences _preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela1);
        init();
    }


    private void init() {
        view = this.findViewById(R.id.tela1View);

        textView = (TextView) findViewById(R.id.tela1TextView);
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

        //
        String azul = getString(R.string.azul);
        if(backgroundColor.equals(azul)) setBackgroundColor(azul, getResources().getColor(R.color.azul));
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

    public void tela1Avancar(View v) {
        redirect(Tela2Activity.class);
    }

    public void tela1Voltar(View v) {
        redirect(MainActivity.class);
    }
}