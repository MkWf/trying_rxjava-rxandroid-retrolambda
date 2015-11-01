package com.gmail.markdevw.tryingrxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;
import rx.Subscription;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tv_result);

        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(v -> runObservable());
    }

    private void runObservable() {
        subscription = Observable.just("Hello")
                .subscribe(s -> textView.setText(s));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }
}
