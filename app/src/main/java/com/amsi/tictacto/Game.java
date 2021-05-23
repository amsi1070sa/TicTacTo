package com.amsi.tictacto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Game extends AppCompatActivity implements View.OnClickListener {

    Button f11, f12, f13, f21,f22, f23, f31, f32, f33;
    String xo = "X";
    int[][] gameStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        f11 = findViewById(R.id.field_1_1);
        f12 = findViewById(R.id.field_1_2);
        f13 = findViewById(R.id.field_1_3);
        f21 = findViewById(R.id.field_2_1);
        f22 = findViewById(R.id.field_2_2);
        f23 = findViewById(R.id.field_2_3);
        f31 = findViewById(R.id.field_3_1);
        f32 = findViewById(R.id.field_3_2);
        f33 = findViewById(R.id.field_3_3);

        f11.setOnClickListener(this);
        f12.setOnClickListener(this);
        f13.setOnClickListener(this);
        f21.setOnClickListener(this);
        f22.setOnClickListener(this);
        f23.setOnClickListener(this);
        f31.setOnClickListener(this);
        f32.setOnClickListener(this);
        f33.setOnClickListener(this);

        gameStorage = new int[3][3];

        initArray(gameStorage);
    }

    private void initArray(int[][] gameStorage) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                gameStorage[x][y] = 0;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.field_1_1:
                f11.setText(xo);
                handleInput(1,1);
                break;
            case R.id.field_1_2:
                f12.setText(xo);
                handleInput(1,2);
                break;
            case R.id.field_1_3:
                f13.setText(xo);
                handleInput(1,3);
                break;
            case R.id.field_2_1:
                f21.setText(xo);
                handleInput(2,1);
                break;
            case R.id.field_2_2:
                f22.setText(xo);
                handleInput(2,2);
                break;
            case R.id.field_2_3:
                f23.setText(xo);
                handleInput(2,3);
                break;
            case R.id.field_3_1:
                f31.setText(xo);
                handleInput(3,1);
                break;
            case R.id.field_3_2:
                f32.setText(xo);
                handleInput(3,2);
                break;
            case R.id.field_3_3:
                f33.setText(xo);
                handleInput(3,3);
                break;
        }
    }

    private void handleInput (int x, int y) {
        if (gameStorage[x-1][y-1] == 0) {
            if (xo.equals("X")) {
                gameStorage[x-1][y-1] = 1;
                xo = "O";
            } else {
                gameStorage[x-1][y-1] = -1;
                xo = "X";
            }
        }
        if (checkGameEnd()) {
          finishGame();
        }
    }

    private void finishGame() {
        if (xo.equals("X")) {
            Toast.makeText(getApplicationContext(), "O gewinnt", Toast.LENGTH_LONG + 10).show();
        } else if (xo.equals("O")) {
            Toast.makeText(getApplicationContext(), "X gewinnt", Toast.LENGTH_LONG + 10).show();
        } else {
            Toast.makeText(getApplicationContext(), "Unentschieden", Toast.LENGTH_LONG + 10).show();
        }
        initArray(gameStorage);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Game.this, Game.class);
                startActivity(intent);
                Game.this.finish();
            }
        }, 6000);
    }


    private boolean checkGameEnd() {
        return (Math.abs(gameStorage[0][0] + gameStorage[0][1] + gameStorage[0][2]) == 3||
                Math.abs(gameStorage[1][0] + gameStorage[1][1] + gameStorage[1][2]) == 3 ||
                Math.abs(gameStorage[2][0] + gameStorage[2][1] + gameStorage[2][2]) == 3 ||
                Math.abs(gameStorage[0][0] + gameStorage[1][0] + gameStorage[2][0]) == 3 ||
                Math.abs(gameStorage[0][1] + gameStorage[1][1] + gameStorage[2][1]) == 3 ||
                Math.abs(gameStorage[0][2] + gameStorage[1][2] + gameStorage[2][2]) == 3 ||
                Math.abs(gameStorage[0][0] + gameStorage[1][1] + gameStorage[2][2]) == 3 ||
                Math.abs(gameStorage[0][2] + gameStorage[1][1] + gameStorage[2][0]) == 3) ||
                isArrayFull(gameStorage);
    }

    private boolean isArrayFull(int[][] gameStorage) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (gameStorage[x][y] == 0) {
                    return false;
                }
            }
        }
        xo = "E";
        return true;
    }
}
