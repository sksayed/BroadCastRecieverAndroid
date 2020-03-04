package lab.itsoul.com.dailygoods.app.checkinternetsayed;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import androidx.core.net.ConnectivityManagerCompat;

public class ConnectivityReciver extends BroadcastReceiver {

    public static ConnectivityRecieverListener recieverListener ;

    public ConnectivityReciver() {
        super();
    }

    public static boolean isConnected () {
        ConnectivityManager manager = (ConnectivityManager) MyApplication.getmInstance().getApplicationContext()
                                        .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
        return isConnected ;
    }

    private  boolean getConnection () {
        ConnectivityManager manager = (ConnectivityManager) MyApplication.getmInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
        return isConnected ;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("sayed","here");

       if(recieverListener != null) {
            recieverListener.onNetworkConnectionChanged(getConnection());
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Action: " + intent.getAction() + "\n");
        sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
        String log = sb.toString();
        Log.d("sayed", log);
        Toast.makeText(context, log, Toast.LENGTH_LONG).show();

    }

    public interface ConnectivityRecieverListener {
        void onNetworkConnectionChanged (boolean isConnected );
    }
}
