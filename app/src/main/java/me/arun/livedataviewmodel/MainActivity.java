package me.arun.livedataviewmodel;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amitshekhar.DebugDB;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.arun.livedataviewmodel.adapter.MainUserAdapter;
import me.arun.livedataviewmodel.model.User;
import me.arun.livedataviewmodel.viewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.firstName)
    EditText etfirstName;
    @BindView(R.id.lastName)
    EditText etlastName;
    @BindView(R.id.age)
    EditText etAge;
    @BindView(R.id.saveUser)
    Button saveUser;
    MainViewModel mainViewModel;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<User> userList;
    String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate: "+DebugDB.getAddressLog());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Initialize the View Model in your layout
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getUserList().observe(this, users -> {
            if (MainActivity.this.userList == null) {
                Log.d(TAG, "onCreate: oncreate size "+users.size());
                setListData(users);
            }
        });

        //Save a new User
        saveUser.setOnClickListener(v -> {
            String firstName = etfirstName.getText().toString().trim();
            String lastName = etlastName.getText().toString().trim();
            String age = etAge.getText().toString().trim();

            if (firstName.isEmpty()) {
                etfirstName.setError("Please Enter First Name");
                return;
            }

            if (lastName.isEmpty()) {
                etlastName.setError("Please Enter Last Name");
                return;
            }

            if (age.isEmpty()) {
                etAge.setError("Please Enter Your Age");
                return;
            }

            saveUser(firstName, lastName, age);

            etAge.setText("");
            etfirstName.setText("");
            etlastName.setText("");

        });

    }

    private void saveUser(String firstName, String lastName, String age) {
        mainViewModel.addUser(new User(
                firstName,
                lastName,
                age
        ));
    }

    public void setListData(final List<User> userList)
    {
        this.userList = userList;
        if (userList.isEmpty()) {
            //Handle No User here - Like show an empty view
        } else {
            MainUserAdapter adapter =
                    new MainUserAdapter(userList, this);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }


}
