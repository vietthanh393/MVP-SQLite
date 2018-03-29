package com.example.mvpdemo.newuser;

import android.content.Context;

import com.example.mvpdemo.data.UserManager;
import com.example.mvpdemo.data.model.User;
import com.example.mvpdemo.newuser.presenter.NewUserPresenter;
import com.example.mvpdemo.newuser.view.NewUserView;

public class NewUserInteract implements NewUserPresenter {
    private NewUserView mNewUserView;
    private UserManager userManager;

    public NewUserInteract(final Context context, final NewUserView view) {
        mNewUserView = view;
        userManager = UserManager.getInstance(context);
    }

    @Override
    public void clear() {
        mNewUserView.onClearText();
    }

    @Override
    public void saveUser(String name, String age) {
        final User user = new User(name, age);
        long rowInserted = userManager.addUser(user);
        if (rowInserted != -1) {
            mNewUserView.onSaveResult(true);
        } else {
            mNewUserView.onSaveResult(false);
        }
    }

    @Override
    public void showProgressBar(int visibility) {
        mNewUserView.onShowProgressBar(visibility);
    }
}
