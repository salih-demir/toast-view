package com.cascade.toastview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showToast(View view) {
        ToastView toastView = new ToastView(this);
        switch (view.getId()) {
            case R.id.b_show_error_toast:
                toastView.show(ToastView.ToastType.ERROR, "ERROR");
                break;
            case R.id.b_show_info_toast:
                toastView.show(ToastView.ToastType.INFORMATION, "INFORMATION");
                break;
            case R.id.b_show_success_toast:
                toastView.show(ToastView.ToastType.SUCCESS, "SUCCESS");
                break;
            case R.id.b_show_warning_toast:
                toastView.show(ToastView.ToastType.WARNING, "WARNING");
                break;
        }
    }
}
