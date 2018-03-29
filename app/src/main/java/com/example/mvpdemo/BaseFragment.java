package com.example.mvpdemo;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.widget.Toast;

public abstract class BaseFragment extends Fragment {
    public final String TAG = this.getClass().getSimpleName();
    private ProgressDialog mDialog;

    /**
     * Show ProgressBar to wait for result
     *
     * @param title title of ProgressBar
     */
    public void showProgressBar(final String title) {
        if (mDialog == null) {
            mDialog = new ProgressDialog(getContext());
            mDialog.setCanceledOnTouchOutside(false);
        }
        mDialog.setTitle(title);
        mDialog.setMessage("Please wait...");
        mDialog.show();
    }

    /**
     * Dismiss ProgressBar if showing
     */
    public void dismissProgressBar() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    /**
     * Show a message to User
     *
     * @param message description
     */
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
