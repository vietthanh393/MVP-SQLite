package com.example.mvpdemo.newuser;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mvpdemo.BaseFragment;
import com.example.mvpdemo.R;
import com.example.mvpdemo.newuser.presenter.NewUserPresenter;
import com.example.mvpdemo.newuser.view.NewUserView;

public class NewUserFragment extends BaseFragment implements NewUserView, View.OnClickListener {

    private EditText mName, mAge;
    private Button mSave;
    private NewUserPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NewUserInteract(getContext(), this);
        presenter.showProgressBar(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_user, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(final View view) {
        mName = (EditText) view.findViewById(R.id.name);
        mAge = (EditText) view.findViewById(R.id.age);
        mSave = (Button) view.findViewById(R.id.save);
        mSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save:
                doSaveUser();
                break;
        }
    }

    private void doSaveUser() {
        presenter.showProgressBar(View.VISIBLE);
        mSave.setEnabled(false);
        String name = mName.getText().toString().trim();
        String age = mAge.getText().toString().trim();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(age)) {
            presenter.clear();
            presenter.saveUser(name, age);
        } else {
            presenter.showProgressBar(View.GONE);
            mSave.setEnabled(true);
            showMessage("Please put the valid name and age");
        }
    }

    @Override
    public void onClearText() {
        mName.setText("");
        mAge.setText("");
    }

    @Override
    public void onSaveResult(boolean isSuccess) {
        presenter.showProgressBar(View.GONE);
        mSave.setEnabled(true);
        if (isSuccess) {
            showMessage("Save successfully!");
        } else {
            showMessage("Fail.");
        }
    }

    @Override
    public void onShowProgressBar(int visibility) {
        if (visibility == View.VISIBLE) {
            showProgressBar("Saving User");
        } else if (visibility == View.GONE) {
            dismissProgressBar();
        }
    }
}
