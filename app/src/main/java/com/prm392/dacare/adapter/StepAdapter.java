package com.prm392.dacare.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.dacare.R;
import com.prm392.dacare.model.Step;

import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    private List<Step> steps;

    public StepAdapter(List<Step> steps) {
        this.steps = steps;
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_step, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        Step step = steps.get(position);
        holder.stepTitle.setText("Bước " + step.getStepNumber() + ": " + step.getStepName());
        holder.stepDescription.setText(step.getStepDescription());

        if (step.getProducts() != null && !step.getProducts().isEmpty()) {
            ProductAdapter productAdapter = new ProductAdapter(step.getProducts());
            holder.rvProductList.setAdapter(productAdapter);
            holder.rvProductList.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        }
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public static class StepViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rvProductList;
        TextView stepTitle, stepDescription;

        public StepViewHolder(@NonNull View itemView) {
            super(itemView);
            stepTitle = itemView.findViewById(R.id.stepTitle);
            stepDescription = itemView.findViewById(R.id.stepDescription);
            rvProductList = itemView.findViewById(R.id.rvProductList);
        }
    }
}
