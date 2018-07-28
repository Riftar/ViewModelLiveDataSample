package me.arun.livedataviewmodel.db;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import me.arun.livedataviewmodel.dao.UserDao;
import me.arun.livedataviewmodel.model.User;


@android.arch.persistence.room.Database(
        entities = {User.class},
        version = 1, exportSchema = false)

public abstract class Database extends RoomDatabase
{

    private static Database INSTANCE;

    public static Database getDatabase(Context context)
    {
        if (INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Database.class, "jetpack_db")
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public abstract UserDao userDao();
}
