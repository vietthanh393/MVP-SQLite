package com.example.mvpdemo.listuser;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvpdemo.R;
import com.example.mvpdemo.data.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ItemHolder> {
    private List<User> userList;

    public UserAdapter(final List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public void setUserList(final List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, int position) {
        final User user = userList.get(position);
        final String name = user.getName();
        final String age = user.getAge();

        holder.userName.setText(name);
        holder.userAge.setText(age);
    }

    @Override
    public int getItemCount() {
        return userList != null ? userList.size() : 0;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        private TextView userName;
        private TextView userAge;

        public ItemHolder(View view) {
            super(view);
            userName = (TextView) view.findViewById(R.id.user_name);
            userAge = (TextView) view.findViewById(R.id.user_age);
        }
    }
}
