package com.example.sqlitedb1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnReg, btnRead, btnSend;
    EditText etLoggin, etPassword;
    DBHelper dbHelper;
    TextView tvLoggin, tvPassword;
    Connector connector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReg = (Button) findViewById(R.id.btnReg);
        btnReg.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);

        etLoggin = (EditText) findViewById(R.id.etLoggin);
        etPassword = (EditText) findViewById(R.id.etPassword);

        tvLoggin = (TextView) findViewById(R.id.tvLog);
        tvPassword = (TextView) findViewById(R.id.tvPas);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View view) {
        String login = etLoggin.getText().toString();
        String password = etPassword.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        switch (view.getId()) {
            case R.id.btnReg:
                contentValues.put(dbHelper.KEY_LOG, login);
                contentValues.put(dbHelper.KEY_PAS, password);

                db.insert(dbHelper.TABLE_USERS, null, contentValues);

                Log.d("mLog","Put to db user with login: "+login);
                break;
            case R.id.btnRead:
                Cursor cursor = db.query(dbHelper.TABLE_USERS, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {

                    int indexLoggin = cursor.getColumnIndex(DBHelper.KEY_LOG);
                    int indexPassword = cursor.getColumnIndex(DBHelper.KEY_PAS);

                    tvLoggin.setText(cursor.getString(indexLoggin));
                    tvPassword.setText(cursor.getString(indexPassword));
                        do {
                            indexLoggin = cursor.getColumnIndex(DBHelper.KEY_LOG);
                            indexPassword = cursor.getColumnIndex(DBHelper.KEY_PAS);
                            Log.d("mLog","Got from db user with login: "+cursor.getString(indexLoggin));
                            Log.d("mLog","and password: "+cursor.getString(indexPassword));
                        }while (cursor.moveToNext());

                } else {
                    tvLoggin.setText("No user in db");
                    Log.d("mLog","user not exists");

                }
                cursor.close();
                break;
            case R.id.btnSend:
                Log.d("mLog","btn Send clicked");
                connector = new Connector();
                connector.communicate(etLoggin.getText().toString());
//                connector.communicate("static text");

                break;
        }
        dbHelper.close();
    }
}
