package com.ckaradimitriou.dietplanapp.util;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    @BindingAdapter("android:setUserInfo")
    public static void setUserInfo(TextView txtView, String value) {
        if (value != null) {
            txtView.setText(value);
        } else {
            txtView.setText("null");
        }
    }
}
