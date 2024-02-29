package com.example.controlesbasicos;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TabHost tbh;
    private EditText txtAguaPotable, txtConversorCantidad;
    Spinner spn;
    Button btnAguaPotable, btnConvertir;

    Superficie ConversorArea = new Superficie();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbh = findViewById(R.id.tbhConversores);
        tbh.setup();

        tbh.addTab(tbh.newTabSpec("Consumo de agua").setContent(R.id.tabAguaPotable).setIndicator("Consumo de agua", null));
        tbh.addTab(tbh.newTabSpec("Conversor de area").setContent(R.id.tabConversor).setIndicator("Conversor de area", null));

        btnAguaPotable = findViewById(R.id.btnAguaPotable);
        btnConvertir = findViewById(R.id.btnConvertir);

        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spn = findViewById(R.id.spnConversorDe);
                int de = spn.getSelectedItemPosition();

                spn = findViewById(R.id.spnConversorA);
                int a = spn.getSelectedItemPosition();

                txtConversorCantidad = findViewById(R.id.txtConversorCantidad);
                double cantidad = Double.parseDouble(txtConversorCantidad.getText().toString());

                double resp = ConversorArea.convertir(0, de, a, cantidad);
                String convertido = String.format("%.4f",resp);
                Toast.makeText(getApplicationContext(),"Respuesta: "+
                        convertido, Toast.LENGTH_LONG).show();
            }
        });

        btnAguaPotable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double cuota;

                txtAguaPotable= findViewById(R.id.txtAguaPotable);
                double calculo = Double.parseDouble(txtAguaPotable.getText().toString());
                if(calculo >= 1 && calculo <= 18){
                    cuota = 6;
                } else if (calculo >= 19 && calculo <= 28) {
                    double excesoDe18 = calculo - 18;
                    cuota = 6.0 + (excesoDe18 * 0.45);
                }else {
                    double excesoDe28 = calculo - 28;
                    double excesoDe18 = 28 - 18;
                    cuota = 6.0 + (excesoDe18 * 0.45) + (excesoDe28 * 0.65);
                }
                Toast.makeText(getApplicationContext(),"La cantidad a pagar es de: $"+
                        cuota, Toast.LENGTH_LONG).show();
            }
        });
    }
}

class Superficie {
    double[][] valores = {
            {1, 0.0015903307888, 10.7639, 1.3949972136, 1.19599, 0.0001428571429, 0.0001}
    };
    public double convertir(int opcion, int de, int a, double cantidad) {
        return valores[opcion][a] / valores[opcion][de] * cantidad;
    }
}






