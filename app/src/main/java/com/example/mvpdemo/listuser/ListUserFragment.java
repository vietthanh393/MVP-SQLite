package com.example.mvpdemo.listuser;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvpdemo.BaseFragment;
import com.example.mvpdemo.R;
import com.example.mvpdemo.ViewPagerVisibilityListener;
import com.example.mvpdemo.data.model.User;
import com.example.mvpdemo.listuser.presenter.ListUserPresenter;
import com.example.mvpdemo.listuser.view.ListUserView;

import java.util.ArrayList;
import java.util.List;

public class ListUserFragment extends BaseFragment implements ListUserView, ViewPagerVisibilityListener {
    private UserAdapter adapter;
    private List<User> userList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListUserPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ListUserInteract(getContext(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_user, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycler(view);
        initSwipeToRefresh(view);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initRecycler(final View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        final DividerItemDecoration itemDecorator = new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable
                .custom_divider));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(itemDecorator);
        adapter = new UserAdapter(userList);
        recyclerView.setAdapter(adapter);
    }

    private void initSwipeToRefresh(final View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_to_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadListUser();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    public void showListUser(@NonNull List<User> userList) {
        if (adapter != null) {
            adapter.setUserList(userList);
        }
    }

    @Override
    public void setRefreshing(final boolean refreshing) {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(refreshing);
                }
            });
        }
    }

    @Override
    public void showLoadingError() {
        showMessage("Error while loading user!");
    }

    @Override
    public void showNoUser() {
        showMessage("No user!");
    }

    @Override
    public void onFragmentVisible() {
        presenter.loadListUser();
    }
}
