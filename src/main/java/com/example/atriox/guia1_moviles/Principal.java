package com.example.atriox.guia1_moviles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Principal extends AppCompatActivity {

    private Button buttonAC;
    private Button buttonDEL;
    private Button buttonANS;
    private Button buttonIGUAL;
    private ArrayList<Button> ListNumers;
    private ArrayList<Button> ListOperations;
    private TextView textViewINPUT;
    private TextView textViewOUTPUT;
    private boolean OpSelected = true;
    private String Retorno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        textViewINPUT = (TextView) findViewById(R.id.TxtInput);
        textViewOUTPUT = (TextView) findViewById(R.id.TxtOutput);

        buttonAC = (Button) findViewById(R.id.BtnAC);
        buttonDEL = (Button) findViewById(R.id.BtnDEL);
        buttonANS = (Button) findViewById(R.id.BtnANS);
        buttonIGUAL = (Button) findViewById(R.id.BtnIgual);

        ListNumers = new ArrayList<>();
        ListNumers.add((Button) findViewById(R.id.BtnCero));
        ListNumers.add((Button) findViewById(R.id.BtnUno));
        ListNumers.add((Button) findViewById(R.id.BtnDos));
        ListNumers.add((Button) findViewById(R.id.BtnTres));
        ListNumers.add((Button) findViewById(R.id.BtnCuatro));
        ListNumers.add((Button) findViewById(R.id.BtnCinco));
        ListNumers.add((Button) findViewById(R.id.BtnSeis));
        ListNumers.add((Button) findViewById(R.id.BtnSiete));
        ListNumers.add((Button) findViewById(R.id.BtnOcho));
        ListNumers.add((Button) findViewById(R.id.BtnNueve));
        ListNumers.add((Button) findViewById(R.id.BtnPunto));

        ListOperations = new ArrayList<>();
        ListOperations.add((Button) findViewById(R.id.BtnSuma));
        ListOperations.add((Button) findViewById(R.id.BtnResta));
        ListOperations.add((Button) findViewById(R.id.BtnMultiplicacion));
        ListOperations.add((Button) findViewById(R.id.BtnDivision));

        buttonAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewINPUT.setText("");
                textViewOUTPUT.setText("");
                OpSelected = true;
            }
        });
        buttonDEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarUltimo();
            }
        });
        buttonANS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewINPUT.setText(Retorno);
            }
        });
        buttonIGUAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //evalua la expresion dada en la entrada y lo muestra en txtResultado
                textViewOUTPUT.setText(Parser.evaluar(textViewINPUT.getText().toString()));
                Retorno = textViewOUTPUT.getText().toString();
            }
        });
        initNumeros();
        initOperaciones();
    }
    private void initNumeros(){
        //recorre todos los botones en la lista y les agrega eventos onClick
        for (final Button btn:ListNumers){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //cada vez que pulse un numero lo concatena al texto
                    textViewINPUT.setText(textViewINPUT.getText().toString() + btn.getText().toString());
                    OpSelected = false;
                }
            });
        }
    }
    private void initOperaciones(){
        //recorre todos los botones en la lista y les agrega eventos onClick
        for (final Button btn:ListOperations){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!OpSelected){// si no hay operacion matematica pulsada con anterioridad
                        //agrega la operacion al texto
                        textViewINPUT.setText(textViewINPUT.getText().toString() + btn.getText().toString());
                        OpSelected=true;// y entonces la operacion matematica ya esta pulsada
                    }
                }
            });
        }
    }
    private void eliminarUltimo(){
        //elimina el ultimo caracter en el TextView
        String str = textViewINPUT.getText().toString();//obtento el texto del TextView
        if (str != null && str.length() > 0 ) {// verifico que no sea nulo y que tenga mas de 1 caracter
            str = str.substring(0, str.length() - 1); // saco una subcadena del texto total - 1 (esto elimina el ultimo)
            if(str.length()>0)//si la longitud ya cortada es mayor a cero
                OpSelected = esOperacion((str.substring(str.length()-1,str.length())));//evaluo si es operacion
            else//si es menor a cero, es decir esta vacio
                OpSelected = true;//guardo como pulsado para evitar poner op matematicas al inicio
        }
        textViewINPUT.setText(str);
    }

    private boolean esOperacion(String txt){//evalua si es operacion matematica
        for (final Button btn:ListOperations){//revizo en la lista de botones
            if(btn.getText().equals(txt)){//comparo si el texto que envio es igual al texto de los botones '+' == '+' -> true
                return true;
            }
        }
        return false;//si no hay ningun texto que coincida entonces no es op matematica
    }
}
