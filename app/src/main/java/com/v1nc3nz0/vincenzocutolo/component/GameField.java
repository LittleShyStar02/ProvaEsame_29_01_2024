package com.v1nc3nz0.vincenzocutolo.component;

import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.v1nc3nz0.vincenzocutolo.MainActivity;
import com.v1nc3nz0.vincenzocutolo.R;

import java.util.concurrent.ThreadLocalRandom;

public class GameField
{

    private final TableLayout field; // tabella del gioco
    private int[][] table; // matrice contenente i valori della tavola di gioco
    int[][] checktable; // serve a controllare il tavolo

    /*
        Otteniamo il tavolo da gioco
     */
    public GameField(int field_id)
    {
        field = MainActivity.getInstance().findViewById(field_id);
        checktable = new int[][] {{1,2,3},{4,5,6},{7,8,0}};
    }

    /*
        Controlla la vittoria
     */
    public boolean checkWin()
    {
        for(int x = 0;x < 3;x++)
        {
            for(int y = 0;y < 3;y++)
            {
                if(table[x][y] != checktable[x][y]) return false;
            }
        }
        return true;
    }

    /*
        Questo metodo permette di stampare il gioco
     */
    private void draw() {
        if (field.getChildCount() != 0) field.removeAllViews();
        TableRow row;
        for (int x = 0; x < 3; x++) {
            row = new TableRow(MainActivity.getInstance().getApplicationContext());
            row.setGravity(Gravity.CENTER);

            for (int y = 0; y < 3; y++)
            {
                Button text = new Button(field.getContext());
                text.setLayoutParams(new TableRow.LayoutParams(250,250));
                text.setGravity(Gravity.CENTER);
                text.setBackground(MainActivity.getInstance().getDrawable(R.drawable.box));
                if(table[x][y] == 0) text.setText("");
                else text.setText(String.valueOf(table[x][y]));
                text.setTextSize(36);
                setMoveEvent(text);
                row.addView(text);
            }
            field.addView(row);
        }
        if(checkWin())
        {
            MainActivity.requestConfirm(field.getContext(),"Hai vinto!");
        }
    }

    /*
        Permettiamo di iniziare il gioco
     */
    public void play()
    {
        table = new int[][] {{1,2,3},{4,5,6},{7,0,8}};
        swapNumbers(100);
        MainActivity.getInstance().getCounter().reset();
        draw();
    }

    /*
        Implementazione dei movimenti
     */
    float x1, x2, y1, y2;
    public void setMoveEvent(Button button)
    {
        button.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        y1 = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        y2 = event.getY();
                        float deltaX = x2 - x1;
                        float deltaY = y2 - y1;

                        if (Math.abs(deltaX) > Math.abs(deltaY)) {
                            if (Math.abs(deltaX) > 100) {
                                //Movimento orizzontale
                                if (x2 > x1) {
                                    // verso destra
                                    for(int x = 0;x < 3;x++)
                                    {
                                        for(int y = 0;y < 2; y++)
                                        {
                                            if(table[x][y+1] == 0)
                                            {
                                                swapTable(x,y,x,y+1);
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    //verso sinistra
                                    for(int x = 0;x < 3;x++)
                                    {
                                        for(int y = 1;y < 3; y++)
                                        {
                                            if(table[x][y-1] == 0)
                                            {
                                                swapTable(x,y,x,y-1);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (Math.abs(deltaY) > 100) {
                                // Movimento verticale
                                if (y2 > y1) {
                                    // Movimento verso il basso
                                    for(int y = 0;y < 3; y++)
                                    {
                                        for(int x = 0;x < 2;x++)
                                        {
                                            if(table[x+1][y] == 0)
                                            {
                                                swapTable(x,y,x+1,y);
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    // Movimento verso l'alto
                                    for(int y = 0;y < 3; y++)
                                    {
                                        for(int x = 1;x < 3;x++)
                                        {
                                            if(table[x-1][y] == 0)
                                            {
                                                swapTable(x,y,x-1,y);
                                                break;
                                            }
                                        }
                                    }

                                }
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }

    /*
        Scambiamo le posizioni dei numeri
     */
    private void swapNumbers(int value)
    {
        int posX_1,posX_2,posY_1,posY_2;

        for(int x = 0;x < value;x++)
        {
            posX_1 = ThreadLocalRandom.current().nextInt(0,3);
            posY_1 = ThreadLocalRandom.current().nextInt(0,3);

            posX_2 = ThreadLocalRandom.current().nextInt(0,3);
            posY_2 = ThreadLocalRandom.current().nextInt(0,3);

            while(posX_1 == posX_2 && posY_1 == posY_2)
            {
                posX_2 = ThreadLocalRandom.current().nextInt(0,3);
                posY_2 = ThreadLocalRandom.current().nextInt(0,3);
            }

            int tmp = table[posX_1][posY_1];
            table[posX_1][posY_1] = table[posX_2][posY_2];
            table[posX_2][posY_2] = tmp;

        }
    }

    /*
        Swap dei numeri della table
     */
    public void swapTable(int x1, int y1, int x2, int y2)
    {
        int tmp = table[x1][y1];
        table[x1][y1] = table[x2][y2];
        table[x2][y2] = tmp;
        MainActivity.getInstance().getCounter().increase();
        draw();
    }

}
