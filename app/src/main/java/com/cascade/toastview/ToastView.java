package com.cascade.toastview;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Salih Demir on 29.05.2017.
 */

public class ToastView extends Toast {
    public enum ToastType {
        ERROR(R.drawable.ic_error_black),
        INFORMATION(R.drawable.ic_info_black),
        SUCCESS(R.drawable.ic_success_black),
        WARNING(R.drawable.ic_warning_black);

        final int drawableResId;

        ToastType(@DrawableRes int drawableResId) {
            this.drawableResId = drawableResId;
        }
    }

    private final static float SUB_MESSAGE_PROPORTION = 0.8f;

    private Context context;
    private ImageView imageViewToastIcon;
    private TextView textViewToastMessage;

    public ToastView(Context context) {
        super(context);
        initialize(context);
    }

    private void initialize(@NonNull Context context) {
        this.context = context;

        View viewToast = View.inflate(context, R.layout.content_toast_view, null);
        imageViewToastIcon = viewToast.findViewById(R.id.iv_toast_icon);
        textViewToastMessage = viewToast.findViewById(R.id.tv_toast_message);

        setDuration(Toast.LENGTH_LONG);
        setView(viewToast);
    }

    public void show(ToastType toastType, String message, String subMessage) {
        SpannableString content;
        if (TextUtils.isEmpty(subMessage)) {
            content = new SpannableString(message);
        } else {
            content = new SpannableString(message + "\n" + subMessage);
            RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(SUB_MESSAGE_PROPORTION);
            content.setSpan(relativeSizeSpan, message.length() + 1, content.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        imageViewToastIcon.setImageResource(toastType.drawableResId);
        textViewToastMessage.setText(content);

        show();
    }

    public void show(ToastType toastType, @StringRes int messageResId, @StringRes int subMessageResId) {
        show(toastType, context.getString(messageResId), context.getString(subMessageResId));
    }

    public void show(ToastType toastType, String message) {
        show(toastType, message, null);
    }

    public void show(ToastType toastType, @StringRes int messageResId) {
        show(toastType, context.getString(messageResId));
    }
}