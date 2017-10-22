package com.example.hacunamatata.rikkeisoft_recycle_view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.hacunamatata.rikkeisoft_recycle_view.constant.Constant;
import com.example.hacunamatata.rikkeisoft_recycle_view.R;
import com.example.hacunamatata.rikkeisoft_recycle_view.db.DBManager;
import com.example.hacunamatata.rikkeisoft_recycle_view.entity.User;

public class UserInfoActivity extends AppCompatActivity {

    private TextView tvID;
    private EditText etName;
    private EditText etPhone;
    private Button btnDelete;
    private Button btnSave;

    private DBManager mDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        //Initialize variables
        tvID = (TextView) findViewById(R.id.tv_id);
        etName = (EditText) findViewById(R.id.et_name);
        etPhone = (EditText) findViewById(R.id.et_phone);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnSave = (Button) findViewById(R.id.btn_save);

        mDBManager = new DBManager(getBaseContext());

        // Get data
        getDataForInsert();

        actionPerformed();
    }

    private void getDataForInsert() {
        Intent intent = getIntent();
        User user = intent.getParcelableExtra(Constant.PARCEL_KEY);
        int id = intent.getIntExtra(Constant.ID, 0);
        tvID.setText(String.valueOf(id));
        etName.setText(user.getName());
        etPhone.setText(user.getPhone());
    }

    private void actionPerformed() {

        // Button Save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                User user = new User(Integer.parseInt(tvID.getText().toString()),
                        etName.getText().toString(), etPhone.getText().toString());
                int d = mDBManager.updateUser(user);
                Log.i("Updated rows: ", String.valueOf(d));
                intent.putExtra(Constant.PARCEL_KEY, user);
                setResult(Constant.UPDATE_USER, intent);
                finish();
            }
        });

        // Button delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(Integer.parseInt(tvID.getText().toString()),
                        etName.getText().toString(), etPhone.getText().toString());
                mDBManager.deleteUser(user);
                Intent intent = new Intent();
                setResult(Constant.DELETE_USER, intent);
                finish();
            }
        });
    }
}
