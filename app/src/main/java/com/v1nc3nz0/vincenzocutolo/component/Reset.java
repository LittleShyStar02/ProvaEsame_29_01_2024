package com.v1nc3nz0.vincenzocutolo.component;

import android.widget.Button;

import com.v1nc3nz0.vincenzocutolo.MainActivity;

public class Reset
{

    private final Button reset; // pulsante di reset

    /*
        Il costruttore fa ottenere il pulsante
        e  inizializza il comportamento
     */
    public Reset(int reset_id)
    {
        reset = MainActivity.getInstance().findViewById(reset_id);
        inizializeButton();
    }

    /*
        Modifichiamo il funzionamento del pulsante
     */
    private void inizializeButton()
    {
        reset.setOnClickListener((event) -> MainActivity.requestConfirm(reset.getContext(),"Conferma reset:"));
    }

}
