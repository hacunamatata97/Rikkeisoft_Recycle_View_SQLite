package com.example.hacunamatata.rikkeisoft_recycle_view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.example.hacunamatata.rikkeisoft_recycle_view.R;
import com.example.hacunamatata.rikkeisoft_recycle_view.constant.Constant;
import com.example.hacunamatata.rikkeisoft_recycle_view.db.DBManager;
import com.example.hacunamatata.rikkeisoft_recycle_view.entity.User;

public class NewUserActivity extends AppCompatActivity implements OnClickListener {

    private EditText etName;
    private EditText etPhone;

    private DBManager mDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        // Initialize variables
        etName = (EditText) findViewById(R.id.et_new_name);
        etPhone = (EditText) findViewById(R.id.et_new_phone);
        Button btnAdd = (Button) findViewById(R.id.btn_add);

        mDBManager = new DBManager(getBaseContext());

        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        User user = new User(MainActivity.users.size() + 1, etName.getText().toString(),
                etPhone.getText().toString());
        mDBManager.insertUser(user);
        intent.putExtra(Constant.PARCEL_KEY, user);
        setResult(Constant.ADD_USER, intent);
        Log.i("Fab click", user.toString());
        finish();
    }
}
