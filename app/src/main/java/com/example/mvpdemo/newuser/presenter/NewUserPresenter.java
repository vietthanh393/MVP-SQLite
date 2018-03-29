package com.example.mvpdemo.newuser.presenter;

public interface NewUserPresenter {
    void clear();

    void saveUser(String name, String age);

    void showProgressBar(int visibility);
}
