package com.gmail.markdevw.tryingrxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import rx.Subscription;

public class MainActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private Button mButton;
    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsername = (EditText) findViewById(R.id.et_username);
        mPassword = (EditText) findViewById(R.id.et_password);

//        mButton = (Button) findViewById(R.id.btn);
//        mSubscription = RxView.clicks(mButton)
//                .subscribe(s -> runObservable());
    }

//    private void runObservable() {
//        mSubscription = Observable.just("Hello")
//                .subscribe(s -> mTextView.setText(s));
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubscription.unsubscribe();
    }
}
