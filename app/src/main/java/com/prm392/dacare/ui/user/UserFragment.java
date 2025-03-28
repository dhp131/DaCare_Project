package com.prm392.dacare.ui.user;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.prm392.dacare.R;
import com.prm392.dacare.ui.LoginActivity;
import com.prm392.dacare.ui.order.ListOrderActivity;
import com.prm392.dacare.utils.SharedPreferencesUtil;
import com.squareup.picasso.Picasso;

public class UserFragment extends Fragment {

    private UserViewModel mViewModel;
    private Button btnLogout, btnOrderList;

    private TextView tvUserName, tvUserEmail;
    private ImageView imgAvatar;
    private ProgressBar pbUser;

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user, container, false);
        mViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        initView(view);
        loadUserInfo();
        return view;
    }

    private void initView(View view){
        tvUserName = view.findViewById(R.id.tvUserName);
        tvUserEmail = view.findViewById(R.id.tvUserEmail);
        imgAvatar = view.findViewById(R.id.imgAvatar);
        pbUser = view.findViewById(R.id.pbUser);
        // Find and set listener for Logout button
        btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferencesUtil.clear();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().finish();
                startActivity(intent);
            }
        });
        btnOrderList = view.findViewById(R.id.btnOrderList);
        btnOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch ListOrderActivity
                Intent intent = new Intent(getActivity(), ListOrderActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadUserInfo(){
        mViewModel.getMessage().observe(getViewLifecycleOwner(), message -> {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        });
        mViewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            if (user == null){
                pbUser.setVisibility(View.VISIBLE);
            } else {
                tvUserName.setText(user.getName());
                tvUserEmail.setText(user.getEmail());
                Picasso.get().load(user.getAvatar()).into(imgAvatar);
                pbUser.setVisibility(View.GONE);
            }

        });

    }
}