package lab.itsoul.com.dailygoods.app.checkinternetsayed;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {

    public static MyApplication mInstance ;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this ;
    }

    public static synchronized MyApplication getmInstance() {
        return mInstance;
    }

    public void setConnectivityListener ( ConnectivityReciver.ConnectivityRecieverListener listener) {
        Log.d("sayed", "listener");
        ConnectivityReciver.recieverListener = listener ;
    }
}
