package com.example.losowaniekostek;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText editTextLiczbaKostek;
    private Button buttonLosuj, buttonRosnaco, buttonMalejaco;
    private GridLayout gridKostki;
    private TextView textViewWyniki;

    private int[] kostki = new int[20];
    private int liczbaKostek = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLiczbaKostek = findViewById(R.id.editTextLiczbaKostek);
        buttonLosuj = findViewById(R.id.buttonLosuj);
        buttonRosnaco = findViewById(R.id.buttonRosnaco);
        buttonMalejaco = findViewById(R.id.buttonMalejaco);
        gridKostki = findViewById(R.id.gridKostki);
        textViewWyniki = findViewById(R.id.textViewWyniki);

        // Przycisk "LOSUJ"
        buttonLosuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tekst = editTextLiczbaKostek.getText().toString().trim();
                liczbaKostek = Integer.parseInt(tekst);

                Random rnd = new Random();
                int suma = 0;

                for (int i = 0; i < liczbaKostek; i++) {
                    kostki[i] = rnd.nextInt(6) + 1;
                    suma += kostki[i];
                }

                wyswietlKostki();
                textViewWyniki.setText("Suma: " + suma);
            }
        });

        // Przycisk "SORTUJ ROSNĄCO"
        buttonRosnaco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < liczbaKostek - 1; i++) {
                    for (int j = 0; j < liczbaKostek - i - 1; j++) {
                        if (kostki[j] > kostki[j + 1]) {
                            int temp = kostki[j];
                            kostki[j] = kostki[j + 1];
                            kostki[j + 1] = temp;
                        }
                    }
                }
                wyswietlKostki();
            }
        });

        // Przycisk "SORTUJ MALEJĄCO"
        buttonMalejaco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < liczbaKostek - 1; i++) {
                    for (int j = 0; j < liczbaKostek - i - 1; j++) {
                        if (kostki[j] < kostki[j + 1]) {
                            int temp = kostki[j];
                            kostki[j] = kostki[j + 1];
                            kostki[j + 1] = temp;
                        }
                    }
                }
                wyswietlKostki();
            }
        });
    }

    // Metoda do wypisania kostek
    private void wyswietlKostki() {
        gridKostki.removeAllViews();
        for (int i = 0; i < liczbaKostek; i++) {
            TextView textv = new TextView(this);
            textv.setText("Kostka " + (i + 1) + ": " + kostki[i]);
            textv.setTextSize(16);
            gridKostki.addView(textv);
        }
    }
}