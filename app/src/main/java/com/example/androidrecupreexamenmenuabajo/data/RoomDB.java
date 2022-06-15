package com.example.androidrecupreexamenmenuabajo.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Nota.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static RoomDB db;
    public abstract NotaDao notaDao();

    public synchronized static RoomDB getInstance(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, "db_notas")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return db;
    }
}
