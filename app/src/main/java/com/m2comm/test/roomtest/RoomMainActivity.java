package com.m2comm.test.roomtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.m2comm.test.R;

import java.util.List;

public class RoomMainActivity extends AppCompatActivity {

    private EditText mTodoEditText;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_main);

        mTodoEditText = findViewById(R.id.todo_edit);
        mResultTextView = findViewById(R.id.result_text);


        /*
        * DB 객체를 만들때 DB는 백그라운드에서 동작하지않으면 에러가남.
        * allowMainThreadQueries() 호출하면 MainThread 에서 작동해도 에러가나지 않음
        * MainThread에서 작동하는 코드는 어디까지나 테스트할때..
        * 백그라운드에서 작업하도록!.
        * */
        final AppDatabase db = Room.databaseBuilder(this , AppDatabase.class ,
                "todo-db")
                .build();

        //UI 갱신
        db.todoDao().getAll().observe(this, todos -> {
            mResultTextView.setText(todos.toString());
        });

        //버튼 클릭시 DB에 Insert
        findViewById(R.id.todo_button).setOnClickListener(v -> {
            new InsertAsyncTask(db.todoDao()).execute(
                    new Todo(mTodoEditText.getText().toString()));
        });
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