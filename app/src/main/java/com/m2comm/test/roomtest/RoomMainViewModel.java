package com.m2comm.test.roomtest;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import java.util.List;

public class RoomMainViewModel extends AndroidViewModel {

    private AppDatabase mDb;
    public LiveData<List<Todo>> mTodos;
    public String mNewTodo;

    public RoomMainViewModel(@NonNull Application application) {
        super(application);

        /*
         * DB 객체를 만들때 DB는 백그라운드에서 동작하지않으면 에러가남.
         * allowMainThreadQueries() 호출하면 MainThread 에서 작동해도 에러가나지 않음
         * MainThread에서 작동하는 코드는 어디까지나 테스트할때..
         * 백그라운드에서 작업하도록!.
         * */
        mDb = Room.databaseBuilder(application , AppDatabase.class ,
                "todo-db").build();
        mTodos = getAll();
    }

    public LiveData<List<Todo>> getAll() {
        return mDb.todoDao().getAll();
    }

    public void insert(String todo) {
        new InsertAsyncTask(mDb.todoDao()).execute(new Todo(todo));
    }

    //구글쪽에서도 작업이 오래걸리는 처리가 생길수도있으니
    // DB 작업은 백그라운드에서 권장하고있음.
    private static class InsertAsyncTask extends AsyncTask<Todo , Void , Void> {

        private TodoDao mTodoDao;

        public InsertAsyncTask(TodoDao todoDao) {
            this.mTodoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            mTodoDao.insert(todos[0]);
            return null;
        }
    }

}
