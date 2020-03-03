package lab.itsoul.com.dailygoods.app.checkinternetsayed;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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
        if(recieverListener != null) {
            recieverListener.onNetworkConnectionChanged(getConnection());
        }

    }

    public interface ConnectivityRecieverListener {
        void onNetworkConnectionChanged (boolean isConnected );
    }
}
