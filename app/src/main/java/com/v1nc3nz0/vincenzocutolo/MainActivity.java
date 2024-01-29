package com.v1nc3nz0.vincenzocutolo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import com.v1nc3nz0.vincenzocutolo.component.Counter;
import com.v1nc3nz0.vincenzocutolo.component.GameField;
import com.v1nc3nz0.vincenzocutolo.component.Reset;

public class MainActivity extends AppCompatActivity
{

    /*
        Miglioramento 1: Aggiunto un textview per il counter
        Miglioramento 2: DialogBox fatta
        Miglioramento 3: Bloccata la rotazione
        Miglioramento 4: Invece del click si può fare lo shift
     */

    private static MainActivity instance; // instanza dell'activity

    private Counter counter; // instanza del counter
    private GameField gamefield; // instanza del campo di gioco
    private Reset reset; // instanza del pulsante di reset

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.instance = this; // inizializziamo l'instanza corrente
        counter = new Counter(R.id.counter); // inizializzazione del counter
        gamefield = new GameField((R.id.gamefield)); // inizializzione del campo di gioco
        reset = new Reset(R.id.reset);
        gamefield.play(); // inizio del gioco

    }

    public static void requestConfirm(Context context, String title)
    {
        new AlertDialog.Builder(context)
                .setTitle(title + " Rigiochiamo?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("SI", (dialog,whichButton) ->
                        MainActivity.getInstance().getGamefield().play())
                .setNegativeButton("NO", (dialog,whichButton)->{})
                .show();
    }

    /*
        Ritorniamo l'instanza corrente della activity
     */
    public static MainActivity getInstance()
    {
        return instance;
    }

    /*
        Ritorniamo l'instanza del counter
     */
    public Counter getCounter()
    {
        return counter;
    }

    /*
        Ritorniamo l'instanza del campo da gioco
     */
    public GameField getGamefield()
    {
        return gamefield;
    }
}