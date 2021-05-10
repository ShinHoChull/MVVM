package com.m2comm.test.rx_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.m2comm.test.R;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.Subject;

public class RxTestActivity extends AppCompatActivity {


    ArrayList<Integer> lists = new ArrayList<Integer>();
    private String TAG = RxTestActivity.class.getSimpleName();

    private Button mBt1;
    private Button mBt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_test);

        Observable<String> simpleObservable =
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                        emitter.onNext("Hello NextVal");
                        emitter.onComplete();
                    }
                });


        simpleObservable.subscribe(new Subject<String>() {
            @Override
            public boolean hasObservers() {
                return false;
            }

            @Override
            public boolean hasThrowable() {
                return false;
            }

            @Override
            public boolean hasComplete() {
                return false;
            }

            @Override
            public @Nullable Throwable getThrowable() {
                return null;
            }

            @Override
            protected void subscribeActual(@NonNull Observer<? super String> observer) {

            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {
                TextView textView = findViewById(R.id.rxtextView);
                textView.setText(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "error: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "complete!");
            }
        });

        init();
        initListener();
    }

    private void initListener() {

        mBt1.setOnClickListener(it->{
            throw new RuntimeException("Test Crash");
        });

        mBt2.setOnClickListener(it->{

        });
    }

    private void init() {
        mBt1 = findViewById(R.id.rx_button);
        mBt2 = findViewById(R.id.rx_button2);
    }


}