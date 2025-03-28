package com.prm392.dacare.ui.routine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.dacare.R;
import com.prm392.dacare.adapter.StepAdapter;
import com.prm392.dacare.model.Routine;
import com.prm392.dacare.model.SkinType;
import com.prm392.dacare.model.Step;
import com.prm392.dacare.utils.SharedPreferencesUtil;

import java.util.List;

public class RoutineFragment extends Fragment {

    private RoutineViewModel routineViewModel;
    private TextView tvTitle;
    private RecyclerView rvStepList;
    private StepAdapter stepAdapter;

    private static final String TAG = "RoutineFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routine, container, false);
        tvTitle = view.findViewById(R.id.tvTitle);
        rvStepList = view.findViewById(R.id.rvStepList);
        rvStepList.setLayoutManager(new LinearLayoutManager(getContext()));

        routineViewModel = new ViewModelProvider(this).get(RoutineViewModel.class);

//        SkinType skinType = null;
//        if (getArguments() != null) {
//            skinType = (SkinType) getArguments().getSerializable("skinType");
//        }
        String skinTypeId = SharedPreferencesUtil.get("SkinType");
        String skinTypeName = SharedPreferencesUtil.get("SkinTypeName");

        if (!skinTypeId.isEmpty()) {
            tvTitle.setText("Loại da của bạn là: " + skinTypeName);
            routineViewModel.getRoutineBySkinType(skinTypeId);
        } else {
            tvTitle.setText("Không có thông tin loại da.");
            Toast.makeText(getContext(), "Chưa có thông tin loại da, không thể lấy routine.", Toast.LENGTH_LONG).show();
        }

        routineViewModel.getRoutineLiveData().observe(getViewLifecycleOwner(), routineResponse -> {
            if (routineResponse != null) {
                List<Step> steps = routineResponse.getSteps();
                if (stepAdapter == null) {
                    stepAdapter = new StepAdapter(steps);
                    rvStepList.setAdapter(stepAdapter);
                } else {
                    stepAdapter.notifyDataSetChanged();
                }
            } else {
                Toast.makeText(getContext(), "Không tìm thấy lộ trình cho loại da này", Toast.LENGTH_LONG).show();
            }
        });

        routineViewModel.getErrorLiveData().observe(getViewLifecycleOwner(), error -> {
            if (error != null) {
                Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}
