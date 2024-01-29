package com.v1nc3nz0.vincenzocutolo.component;

import android.widget.Button;
import android.widget.TextView;

import com.v1nc3nz0.vincenzocutolo.MainActivity;

public class Counter
{

    /*
        TextView che contiene il valore die movimenti
     */
    private TextView counter;

    /*
        Numero di movimenti effettuato
     */
    private int value;

    /*
        Il costruttore inizializza i componenti della classe
        Inoltre aggiorna il valore del contatore
     */
    public Counter(int counter_id)
    {
        counter = MainActivity.getInstance().findViewById(counter_id);
        value = 0;
        update();
    }

    /*
        Aumentiamo il valore del contatore di 1
     */
    public void increase()
    {
        value++;
        update();
    }

    /*
        Diminuiamo il valore del contatore
     */
    public void reset()
    {
        value = 0;
        update();
    }

    /*
        Aggiorniamo il valore del contatore
        mostrato durante il gioco
     */
    public void update()
    {
        counter.setText(String.valueOf(value));
    }

}
