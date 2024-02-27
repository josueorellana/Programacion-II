package com.example.controlesbasicos;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {
    TabHost tbh;
     private EditText txtAguaPotable, txtConversorCantidad;
    Spinner spn;
    Button btnAguaPotable, btnConversorConvertir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbh = findViewById(R.id.tbhConversores);
        tbh.setup();

        tbh.addTab(tbh.newTabSpec("Consumo de agua potable").setContent(R.id.tabAguaPotable).setIndicator("Consumo de agua potable", null));
        tbh.addTab(tbh.newTabSpec("Conversor de area").setContent(R.id.tabConversor).setIndicator("Conversor de area", null));
    }
    class Superficie{
        double[][] valores = {
                {1,100, 39.3701, 3.280841666667, 1.193, 1.0936138888889999077,
                        0.001, 0.000621371}
        };
        public double convertir(int opcion, int de, int a, double cantidad){
            return valores[opcion][a] / valores[opcion][de] * cantidad;
        }

    }
    }

