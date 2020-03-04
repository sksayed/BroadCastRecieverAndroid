package lab.itsoul.com.dailygoods.app.checkinternetsayed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements ConnectivityReciver.ConnectivityRecieverListener {

    private Button checkConnection ;
    private FloatingActionButton goToNewActivity ;
    private TextView textView ;
    ConnectivityReciver reciver;
    IntentFilter filter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkConnection = findViewById(R.id.checkConnection);
        goToNewActivity = findViewById(R.id.goToNewActivity);
        textView = findViewById(R.id.textView);
        init();
        //checkConnection();
        reciver = new ConnectivityReciver();
        filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);

    }

    private void init() {
        checkConnection.setOnClickListener(v -> {
           checkConnection();
        });

        this.goToNewActivity.setOnClickListener(v -> {
            startActivity( new Intent(MainActivity.this , SecondActivity.class));
        });
    }

    private void checkConnection() {
        boolean isConnected = ConnectivityReciver.isConnected() ;
        showSnakbar(isConnected);
    }

    private void showSnakbar(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = "Good! Connected to Internet";
            color = Color.WHITE;
        } else {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
        }

        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.goToNewActivity), message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.registerReceiver(reciver, filter);
        ((MyApplication)getApplication()).setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnakbar(isConnected);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(reciver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
