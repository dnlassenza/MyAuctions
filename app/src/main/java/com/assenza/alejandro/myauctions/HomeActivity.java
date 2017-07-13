package com.assenza.alejandro.myauctions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    private static final int REQUEST_LOGIN = 0;

    private long userID;
    private FloatingActionButton _newAuction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        _newAuction = (FloatingActionButton)findViewById(R.id.newAuction);
        userID = -1;

        if (userID == -1) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, REQUEST_LOGIN);
        }


        _newAuction.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_LOGIN) {
            if (resultCode == Activity.RESULT_OK) {
                String userId = data.getStringExtra("id");
                Log.d(">> ", userId);
                this.userID = Long.valueOf(userId);
            }
        }
    }

}
