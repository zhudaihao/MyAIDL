package cn.zdh.myaidl;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

/**
 * 实现aidl接口方法
 */
public class AidlService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }


    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
            }

        }
    };


    /**
     * 获取aidl的binder对象
     * 注意在子线程
     */
    private IBinder iBinder = new IMyAidlInterface.Stub() {
        @Override
        public void login(Bean bean) throws RemoteException {
            //判断用户账户是的正确
            String name = bean.getName();
            String password = bean.getPassword();
            Message message = handler.obtainMessage();
            if (name.equals("张三") && password.equals("123456")) {
                message.what = 1;
                handler.sendMessage(message);
            } else {
                Log.e("zdh", "------" + Thread.currentThread().getName());
                message.what = 0;
                handler.sendMessage(message);
            }


        }
    };
}
