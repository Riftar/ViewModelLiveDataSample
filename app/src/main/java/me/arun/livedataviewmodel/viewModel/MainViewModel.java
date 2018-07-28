package me.arun.livedataviewmodel.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;


import java.util.List;

import me.arun.livedataviewmodel.db.Database;
import me.arun.livedataviewmodel.model.User;

public class MainViewModel extends AndroidViewModel
{

    private Database appDatabase;
    private final LiveData<List<User>> userList;
    String TAG="MainViewModel";

    public MainViewModel(@NonNull Application application)
    {
        super(application);
        appDatabase = Database.getDatabase(this.getApplication());
        //Initialize User List
        userList = appDatabase.userDao().getAllUsers();
        Log.d(TAG, "MainViewModel: size "+userList.getValue());
    }

    //Add New User
    public void addUser(User user)
    {
        new addAsyncTask(appDatabase).execute(user);
    }

    // Get Users
    public LiveData<List<User>> getUserList()
    {
        return userList;
    }

    private static class addAsyncTask extends AsyncTask<User, Void, Void>
    {

        private Database db;

        addAsyncTask(Database appDatabase)
        {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final User... params)
        {
            db.userDao().addUser(params[0]);
            return null;
        }

    }
}
