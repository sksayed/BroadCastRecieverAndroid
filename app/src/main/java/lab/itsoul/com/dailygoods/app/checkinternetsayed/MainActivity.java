package lab.itsoul.com.dailygoods.app.checkinternetsayed;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkConnection = findViewById(R.id.checkConnection);
        goToNewActivity = findViewById(R.id.goToNewActivity);
        textView = findViewById(R.id.textView);
        init();
        checkConnection();
    }

    private void init() {
        checkConnection.setOnClickListener(v -> {
           checkConnection();
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
        MyApplication.getmInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnakbar(isConnected);
    }
}
