package cn.zdh.myaidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class TextActivity extends AppCompatActivity {

    private IMyAidlInterface iMyAidlInterface;
    private ServiceConnection conn;
    private EditText etName, etPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        etName = findViewById(R.id.et_name);
        etPassword = findViewById(R.id.et_password);


        //bindService启动服务
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("cn.zdh.myaidl", "cn.zdh.myaidl.AidlService"));
        conn = new MyServiceConnection();
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除服务绑定
        unbindService(conn);
    }

    //登录
    public void load(View view) {
        Bean bean = new Bean();
        bean.setName(etName.getText().toString());
        bean.setPassword(etPassword.getText().toString());
        try {
            //添加数据
            iMyAidlInterface.login(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取aidl接口对象
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

}
