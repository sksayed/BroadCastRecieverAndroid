package lab.itsoul.com.dailygoods.app.checkinternetsayed;

import android.app.Application;

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
        ConnectivityReciver.recieverListener = listener ;
    }
}
