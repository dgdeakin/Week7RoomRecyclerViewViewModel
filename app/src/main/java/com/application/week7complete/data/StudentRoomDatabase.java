package com.application.week7complete.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//The database class for Room must be abstract and extend RoomDatabase

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentRoomDatabase extends RoomDatabase {

    public abstract StudentDAO studentDAO();

    private static volatile StudentRoomDatabase INSTANCE;
    static final int NUMBER_OF_THREADS = 4;
//    ExecutorService with a fixed thread pool that you will
//    use to run database operations asynchronously on a background thread.
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static StudentRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (StudentRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StudentRoomDatabase.class, "student_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                StudentDAO dao = INSTANCE.studentDAO();
                dao.deleteAll();

//                Word word = new Word("Hello");
//                dao.insert(word);
//                word = new Word("World");
//                dao.insert(word);
            });
        }
    };

}
