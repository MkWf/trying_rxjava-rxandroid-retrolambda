package com.gmail.markdevw.tryingrxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import rx.Observable;
import rx.Subscription;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private Button mButton;
    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.tv_result);

        mButton = (Button) findViewById(R.id.btn);
        mSubscription = RxView.clicks(mButton)
                .subscribe(s -> runObservable());
    }

    private void runObservable() {
        mSubscription = Observable.just("Hello")
                .subscribe(s -> mTextView.setText(s));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubscription.unsubscribe();
    }
}
