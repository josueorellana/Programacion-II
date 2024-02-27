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

        tbh.addTab(tbh.newTabSpec("Consumo de agua potable").setContent(R.id.tabAguaPotable).setIndicator("Consumo de agua potable", null));
        tbh.addTab(tbh.newTabSpec("Conversor de area").setContent(R.id.tabConversor).setIndicator("Conversor de area", null));

        btnConvertir = findViewById(R.id.btnConvertir);
        btnAguaPotable = findViewById(R.id.btnAguaPotable);
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
                Toast.makeText(getApplicationContext(),"Respuesta: "+
                        resp, Toast.LENGTH_LONG).show();
            }
        });
    }
}

class Superficie {
    double[][] valores = {
            {1, 629.856, 0.0929, 0.835905, 0.836, 6988.96, 10000}
    };

    public double convertir(int opcion, int de, int a, double cantidad) {
        return valores[opcion][a] / valores[opcion][de] * cantidad;

    }


}



