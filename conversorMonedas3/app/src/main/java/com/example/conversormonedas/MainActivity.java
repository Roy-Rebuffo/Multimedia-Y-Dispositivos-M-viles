package com.example.conversormonedas;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    EditText DE,DY,DL;
    double VDE = 0.86,VDY=160.56,VDL=0.76;
    TextView ED,EY,EL,YD,YE,YL,LD,LE,LY;
    ImageView img1,img2,img3,img4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        img1 = findViewById(R.id.imgCee);
        img2 = findViewById(R.id.imgUsa);
        img3 = findViewById(R.id.imgJapon);
        img4 = findViewById(R.id.imgIngla);

        Glide.with(this).load(R.drawable.cee).into(img1);
        Glide.with(this).load(R.drawable.usa).into(img2);
        Glide.with(this).load(R.drawable.japon).into(img3);
        Glide.with(this).load(R.drawable.ingla).into(img4);

        DE = findViewById(R.id.iptDE);DY = findViewById(R.id.iptDY);DL = findViewById(R.id.iptDL);

        ED = findViewById(R.id.iptED);EY = findViewById(R.id.iptEY);EL = findViewById(R.id.iptEL);

        YD = findViewById(R.id.iptYD);YE = findViewById(R.id.iptYE);YL = findViewById(R.id.iptYL);

        LD = findViewById(R.id.iptLD);LE = findViewById(R.id.iptLE);LY = findViewById(R.id.iptLY);

        cambio();
        /******************************************************************************************/
        DE.setOnEditorActionListener((TextView v, int actionId, KeyEvent event)-> {
            VDE = !DE.getText().toString().isEmpty() ? Double.valueOf(DE.getText().toString()) : 0.0;
            cambio();

            return false;
        });
        /******************************************************************************************/
        DY.setOnEditorActionListener((TextView v, int actionId, KeyEvent event)-> {
            VDY = !DY.getText().toString().isEmpty() ? Double.valueOf(DY.getText().toString()) : 0.0;
            cambio();

            return false;
        });
        /******************************************************************************************/
        DL.setOnEditorActionListener((TextView v, int actionId, KeyEvent event)-> {
            VDL = !DL.getText().toString().isEmpty() ? Double.valueOf(DL.getText().toString()) : 0.0;
            cambio();

            return false;
        });
    }
    public void cambio(){
        DE.setText(String.valueOf(VDE));
        DY.setText(String.valueOf(VDY));
        DL.setText(String.valueOf(VDL));

        ED.setText(String.format("%.6f",1/VDE));
        EY.setText(String.format("%.6f",VDY/VDE));
        EL.setText(String.format("%.6f",VDL/VDE));

        YD.setText(String.format("%.6f",1/VDY));
        YE.setText(String.format("%.6f",VDY/VDE));
        YL.setText(String.format("%.6f",VDL/VDY));

        LD.setText(String.format("%.6f",1/VDL));
        LE.setText(String.format("%.6f",VDE/VDL));
        LY.setText(String.format("%.6f",VDY/VDL));
    }
}