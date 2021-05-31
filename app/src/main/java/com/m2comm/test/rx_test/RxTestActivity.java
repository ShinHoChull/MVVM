package com.m2comm.test.rx_test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.subjects.Subject;

public class RxTestActivity extends AppCompatActivity {


    ArrayList<Integer> lists = new ArrayList<Integer>();
    private String TAG = RxTestActivity.class.getSimpleName();

    private Button mBt1;
    private Button mBt2;
    private TextView rxtextView;

    private Disposable mDisposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_test);

        init();
        initListener();
    }

    private void initListener() {

        mBt1.setOnClickListener( it-> {
            throw new RuntimeException("Test Crash");
        });

        mBt2.setOnLongClickListener(it->{
            Log.d(TAG, "longClick!");
            return false;
        });
    }

    private void init() {
        mBt1 = findViewById(R.id.rx_button);
        mBt2 = findViewById(R.id.rx_button2);
        rxtextView = findViewById(R.id.rxtextView);


        Observable<String> source1 = Observable.create(emitter -> {
            emitter.onNext("Hello");
            emitter.onNext("Yena");
            emitter.onComplete();
        });
        source1.subscribe(System.out::println);

        Observable<String> source2 = Observable.just("Hello2", "Yena2");
        source2.subscribe(System.out::println);

    }


}