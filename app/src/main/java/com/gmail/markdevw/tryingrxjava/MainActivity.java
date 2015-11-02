package com.gmail.markdevw.tryingrxjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import rx.Observable;
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

        mButton = (Button) findViewById(R.id.btn);
        mButton.setEnabled(false);

        RxView.clicks(mButton)
                .subscribe(s -> startNextActivity());

        mUsername = (EditText) findViewById(R.id.et_username);
        mPassword = (EditText) findViewById(R.id.et_password);

        initLoginObserver();
    }

    private void initLoginObserver() {
        Observable<CharSequence> obsUsername = RxTextView.textChanges(mUsername);
        Observable<CharSequence> obsPassword = RxTextView.textChanges(mPassword);

        mSubscription = Observable.combineLatest(obsUsername, obsPassword, (a, b) -> {
            return a.length() > 5 && b.length() > 5;
        })
                .subscribe(o -> mButton.setEnabled(o));
    }

    private void startNextActivity() {
        Intent nextActivity = new Intent(this, SecondActivity.class);
        startActivity(nextActivity);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubscription.unsubscribe();
    }
}
