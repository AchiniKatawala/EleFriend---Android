package com.example.achini.elefriend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VerifyCodeActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);

        button = (Button) findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMapsActivity();

            }
        });
    }

    public void openMapsActivity(){
        Intent intent1 = new Intent(this, MapsActivity.class);
        /*Intent intent2 = new Intent(this, MapsActivity.class);*/
        startActivity(intent1);
        /*startActivity(intent2);*/

    }
}
