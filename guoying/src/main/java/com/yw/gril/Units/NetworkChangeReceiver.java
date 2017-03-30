package com.yw.gril.Units;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by 伟 on 2016/5/19.
 *
 * <receiver android:name="com.renrensai.billiards.tools.NetworkChangeReceiver">
 *  if (!NetworkChangeReceiver.isNetworkAvailable(this)) {
     RemindAlertDialogHelper.showNitworkState(this);
    return;
 }
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
    private static final String TAG="NetworkChangeReceiver";
    private static Context context;
    private static boolean IsNetworkConnection=false;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context=context;
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();

        if(networkInfo!=null&&networkInfo.isAvailable())
        {
            Toast.makeText(context,"有网络", Toast.LENGTH_LONG).show();
            IsNetworkConnection=true;
        }
        else
        {
            Toast.makeText(context,"没有网络", Toast.LENGTH_LONG).show();
            IsNetworkConnection=false;
        }
    }

    /**
     * 获取网络连接状态
     * @return
     */
    public static boolean getIsNetworkConnection()
    {
        if(!IsNetworkConnection)
        {

        }
        else
        {

        }
        return IsNetworkConnection;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
        } else {
            //如果仅仅是用来判断网络连接
            //则可以使用 cm.getActiveNetworkInfo().isAvailable();
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
