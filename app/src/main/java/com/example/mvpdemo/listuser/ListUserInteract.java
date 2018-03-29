package com.example.mvpdemo.listuser;

import android.content.Context;

import com.example.mvpdemo.data.model.User;
import com.example.mvpdemo.data.UserManager;
import com.example.mvpdemo.listuser.presenter.ListUserPresenter;
import com.example.mvpdemo.listuser.view.ListUserView;

import java.util.List;

public class ListUserInteract implements ListUserPresenter {
    private ListUserView mListUserView;
    private UserManager userManager;

    public ListUserInteract(final Context context, final ListUserView view) {
        mListUserView = view;
        userManager = UserManager.getInstance(context);
    }

    @Override
    public void loadListUser() {
        mListUserView.setRefreshing(true);
        final List<User> userList = userManager.getAllUsers();
        mListUserView.setRefreshing(false);
        if (userList == null) {
            mListUserView.showLoadingError();
        } else {
            if (userList.isEmpty()) {
                mListUserView.showNoUser();
            } else {
                mListUserView.showListUser(userList);
            }
        }
    }
}
