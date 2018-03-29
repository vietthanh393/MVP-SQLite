package com.example.mvpdemo.listuser.view;

import android.support.annotation.NonNull;

import com.example.mvpdemo.data.model.User;

import java.util.List;

public interface ListUserView {
    void showListUser(@NonNull List<User> userList);

    void setRefreshing(boolean refreshing);

    void showLoadingError();

    void showNoUser();
}
