package com.example.sqlitedb1;

import JsonParser.JsonParser;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.json.simple.JSONObject;

/**
 * Created by Павел on 20.09.2016.
 */
public class InfoActivity extends AppCompatActivity implements View.OnClickListener {
    TextView login;
    TextView fullName;
    TextView address;
    TextView phone;
    TextView orders;
    Button read;

    JsonParser parser = new JsonParser();
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);

        Log.d("mLog","Start");
        login = (TextView) findViewById(R.id.loginInfoId);
        fullName = (TextView) findViewById(R.id.fullNameInfoId);
        address = (TextView) findViewById(R.id.addressInfoId);
        phone = (TextView) findViewById(R.id.phoneInfoId);
        orders = (TextView) findViewById(R.id.ordersInfoId);

        read = (Button) findViewById(R.id.readInfoBtnId);
        read.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.readInfoBtnId: {
                Log.d("mLog","Btn pressed");
                JSONObject clientInfo = parser.parseUserInfo();
                login.append(clientInfo.get("login").toString());
                fullName.append(clientInfo.get("fullName").toString());
                address.append(clientInfo.get("address").toString());
                phone.append(clientInfo.get("phone").toString());
                orders.append(clientInfo.get("orders").toString());
                Log.d("mLog","read");
                break;
            }
        }
    }
}
