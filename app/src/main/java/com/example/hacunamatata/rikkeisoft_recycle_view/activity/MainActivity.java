package com.example.hacunamatata.rikkeisoft_recycle_view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.hacunamatata.rikkeisoft_recycle_view.R;
import com.example.hacunamatata.rikkeisoft_recycle_view.adapter.UserAdapter;
import com.example.hacunamatata.rikkeisoft_recycle_view.constant.Constant;
import com.example.hacunamatata.rikkeisoft_recycle_view.db.DBManager;
import com.example.hacunamatata.rikkeisoft_recycle_view.decoration.MyItemDecorator;
import com.example.hacunamatata.rikkeisoft_recycle_view.entity.User;
import com.facebook.stetho.Stetho;
import java.util.ArrayList;
import java.util.List;

import static com.example.hacunamatata.rikkeisoft_recycle_view.constant.Constant.ADD_USER;
import static com.example.hacunamatata.rikkeisoft_recycle_view.constant.Constant.DELETE_USER;
import static com.example.hacunamatata.rikkeisoft_recycle_view.constant.Constant.PARCEL_KEY;
import static com.example.hacunamatata.rikkeisoft_recycle_view.constant.Constant.REQUEST_START_DETAIL;
import static com.example.hacunamatata.rikkeisoft_recycle_view.constant.Constant.UPDATE_USER;

public class MainActivity extends AppCompatActivity implements UserAdapter.OnItemClickListener {

    Toolbar toolbar;
    FloatingActionButton fab;
    static List<User> users = new ArrayList<>();
    private RecyclerView mRcUser;
    private UserAdapter adapter;
    private boolean isViewAsList = true;
    private int updatePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stetho.initializeWithDefaults(this);

        final DBManager dbManager = new DBManager(this);

        // Delete all records in table
        dbManager.removeAllData();

        // add data to database
        addMoreData();

        // Initialize variables
        users = dbManager.getAllUser();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        mRcUser = (RecyclerView) findViewById(R.id.rc_users);
        adapter = new UserAdapter(users);
        adapter.setItemClickListener(this);

        mRcUser.setAdapter(adapter);
        mRcUser.setHasFixedSize(true);
        mRcUser.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRcUser.addItemDecoration(new MyItemDecorator(10));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), NewUserActivity.class);
                startActivityForResult(intent, REQUEST_START_DETAIL);
            }
        });
    }

    @Override
    public void onItemClickListener(int position) {
        User user = users.get(position);
        Intent intent = new Intent(this, UserInfoActivity.class);
        intent.putExtra(Constant.ID, user.getId());
        intent.putExtra(PARCEL_KEY, user);
        updatePosition = position;
        startActivityForResult(intent, REQUEST_START_DETAIL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case ADD_USER:
                addItem(data);
                break;
            case UPDATE_USER:
                updateItem(data);
                break;
            case DELETE_USER:
                deleteItem();
                break;
        }
    }

    private void addItem(Intent data) {
        User user = data.getParcelableExtra(PARCEL_KEY);
        if (user != null) {
            users.add(user);
            adapter.notifyItemChanged(users.size() - 1);
            adapter.notifyDataSetChanged();
        }
    }

    private void updateItem(Intent data) {
        User user = data.getParcelableExtra(PARCEL_KEY);
        users.set(updatePosition, user);
        adapter.notifyItemChanged(updatePosition);
        adapter.notifyDataSetChanged();
    }

    private void deleteItem() {
        users.remove(updatePosition);
        adapter.notifyItemChanged(updatePosition);
        adapter.notifyDataSetChanged();
    }

    private void addMoreData() {
        DBManager dbManager = new DBManager(this);
        List<User> userList = new ArrayList<>();
        userList.add(new User("Nguyễn Thành Quý", "1"));
        userList.add(new User("Vũ Đức Tùng", "1"));
        userList.add(new User("Bùi Văn Phúc", "1"));
        userList.add(new User("Nguyễn Vũ Hán", "1"));
        userList.add(new User("Tạ Quang Long", "1"));
        userList.add(new User("Nguyễn Đức Thiện", "1"));
        userList.add(new User("Đặng Thành Đạt", "1"));
        userList.add(new User(" Dương Quốc Chí", "1"));
        userList.add(new User("Phan Thị Ngọc", "1"));
        userList.add(new User("Lê Tiến Đạt", "1"));
        userList.add(new User("Khuất Trọng Hiếu", "1"));
        userList.add(new User("Nguyễn Anh Tú", "1"));
        userList.add(new User("Trịnh Đức Huy", "1"));
        userList.add(new User("Võ Hữu Thịnh", "1"));

        userList.add(new User("Nguyễn Thành Quý", "1"));
        userList.add(new User("Vũ Đức Tùng", "1"));
        userList.add(new User("Bùi Văn Phúc", "1"));
        userList.add(new User("Nguyễn Vũ Hán", "1"));
        userList.add(new User("Tạ Quang Long", "1"));
        userList.add(new User("Nguyễn Đức Thiện", "1"));
        userList.add(new User("Đặng Thành Đạt", "1"));
        userList.add(new User(" Dương Quốc Chí", "1"));
        userList.add(new User("Phan Thị Ngọc", "1"));
        userList.add(new User("Lê Tiến Đạt", "1"));
        userList.add(new User("Khuất Trọng Hiếu", "1"));
        userList.add(new User("Nguyễn Anh Tú", "1"));
        userList.add(new User("Trịnh Đức Huy", "1"));
        userList.add(new User("Võ Hữu Thịnh", "1"));

        for (User user : userList) {
            dbManager.insertUser(user);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.getItem(1).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle action bar item clicks here. The action bar will
        //automatically handle clicks on the Home/Up button, so long
        //as you specify a parent activity in AndroidManifest.xml.
        if (item.getItemId() == R.id.toggle_linear) {
            isViewAsList = !isViewAsList;
            supportInvalidateOptionsMenu();

            setScrollPosition(mRcUser.getLayoutManager());

            mRcUser.setLayoutManager(
                    isViewAsList ? new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL,
                            false)
                            : new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

            //Not working
            //                item.setIcon(isViewAsList ? R.drawable.ic_view_module_black_24dp
            //                        : R.drawable.ic_view_list_black_24dp);

            adapter.notifyDataSetChanged();
        }
        return false;
    }

    private void setScrollPosition(RecyclerView.LayoutManager mLayoutManager) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mLayoutManager != null) {
            scrollPosition =
                    ((LinearLayoutManager) mRcUser.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        }

        mRcUser.scrollToPosition(scrollPosition);
    }
}
