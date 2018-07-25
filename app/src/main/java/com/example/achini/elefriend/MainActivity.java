package com.example.achini.elefriend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button button;
    EditText PhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PhoneNumber = (EditText)findViewById(R.id.etPhoneNumber);

        button = (Button) findViewById(R.id.button4);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openVerifyCodeActivity();
//            }
//        });

///////////////////////////////////////////////////////////////////////




    }

    public void onLogin(View view) {
        String phone = PhoneNumber.getText().toString();
        String type = "login";

        System.out.println("inside onlogin");
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, phone);
    }


    /////////////////////////////////////////////////////////////////////////

//    public void openVerifyCodeActivity(){
//        Intent intent = new Intent(this, VerifyCodeActivity.class);
//        startActivity(intent);
//    }
}
