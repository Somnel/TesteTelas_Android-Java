package com.somnel.telasstaandroidjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private View view;
    private String backgroundColor;
    private SharedPreferences _preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init(); // Inicializa
    }

    private void init() {
        // View
        view = this.findViewById(R.id.mainView);

        // TextView
        textView = (TextView) findViewById(R.id.mainTextView);
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


    public void setMainViewAzul(View v) {
        setBackgroundColor(getString(R.string.azul), getResources().getColor(R.color.azul));
    }
    public void setMainViewVermelho(View v) {
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


    public void toTela1(View v) {
        // Salva valor atual
        Intent in = new Intent(this, tela1.class);
        in.putExtra(getString(R.string.valorAtual), textView.getText().toString());

        // Salva cor atual
        SharedPreferences.Editor editor = _preferences.edit();
        editor.putString(getString(R.string.preferColor), backgroundColor);
        editor.apply();

        // Instância
        startActivity(in);
    }


}