package com.example.losowaniekostek;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText editTextLiczbaKostek;
    private Button buttonLosuj, buttonRosnaco, buttonMalejaco;
    private GridLayout gridKostki;
    private TextView textViewWyniki;

    // max 100 kostek
    private int[] kostki = new int[100];
    private int liczbaKostek = 0;
    private boolean czyWylosowano = false;

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

        // maksymalnie 5 kolumn w siatce
        gridKostki.setColumnCount(5);

        // przycisk "losuj"
        buttonLosuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tekst = editTextLiczbaKostek.getText().toString().trim();

                // walidacja czy pole jest puste
                if (tekst.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Wpisz liczbę kostek", Toast.LENGTH_SHORT).show();
                    return;
                }

                liczbaKostek = Integer.parseInt(tekst);

                // walidacja od 1 do 100 kostek
                if (liczbaKostek < 1 || liczbaKostek > 100) {
                    Toast.makeText(MainActivity.this, "Liczba kostek musi być z zakresu od 1 do 100", Toast.LENGTH_LONG).show();
                    return;
                }

                Random rnd = new Random();
                for (int i = 0; i < liczbaKostek; i++) {
                    kostki[i] = rnd.nextInt(6) + 1; // od 1 do 6
                }

                czyWylosowano = true;
                odswiezWidok();
            }
        });

        // przycisk "sortuj rosnąco"
        buttonRosnaco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!czyWylosowano) {
                    Toast.makeText(MainActivity.this, "Najpierw trzeba wylosować kostki", Toast.LENGTH_SHORT).show();
                    return;
                }

                // sortowanie bąbelkowe rosnąco
                for (int i = 0; i < liczbaKostek - 1; i++) {
                    for (int j = 0; j < liczbaKostek - i - 1; j++) {
                        if (kostki[j] > kostki[j + 1]) {
                            int temp = kostki[j];
                            kostki[j] = kostki[j + 1];
                            kostki[j + 1] = temp;
                        }
                    }
                }
                odswiezWidok();
            }
        });

        // przycisk "sortuj malejąco"
        buttonMalejaco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!czyWylosowano) {
                    Toast.makeText(MainActivity.this, "Najpierw trzeba wylosować kostki", Toast.LENGTH_SHORT).show();
                    return;
                }

                // sortowanie bąbelkowe malejąco
                for (int i = 0; i < liczbaKostek - 1; i++) {
                    for (int j = 0; j < liczbaKostek - i - 1; j++) {
                        if (kostki[j] < kostki[j + 1]) {
                            int temp = kostki[j];
                            kostki[j] = kostki[j + 1];
                            kostki[j + 1] = temp;
                        }
                    }
                }
                odswiezWidok();
            }
        });
    }

    // metoda która aktualizuje widok siatki oraz tekst z wynikami po przecinku
    private void odswiezWidok() {
        gridKostki.removeAllViews();
        StringBuilder wynikiTekst = new StringBuilder();

        for (int i = 0; i < liczbaKostek; i++) {
            // dodanie elementu do siatki GridLayout
            TextView tv = new TextView(this);
            tv.setText(String.valueOf(kostki[i]));
            gridKostki.addView(tv);

            // ciąg tekstowy oddzielony przecinkami
            wynikiTekst.append(kostki[i]);
            if (i < liczbaKostek - 1) {
                wynikiTekst.append(", ");
            }
        }

        // wyświetlenie listy wyników rozdzielonych przecinkami w polu tekstowym
        textViewWyniki.setText(wynikiTekst.toString());
    }
}