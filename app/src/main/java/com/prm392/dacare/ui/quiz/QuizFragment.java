package com.prm392.dacare.ui.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.prm392.dacare.R;
import com.prm392.dacare.model.QuizAnswer;
import com.prm392.dacare.model.SkinType;
import com.prm392.dacare.viewmodel.QuizViewModel;

public class QuizFragment extends Fragment {

    private QuizViewModel viewModel;
    private TextView questionTextView, feedbackTextView;
    private RadioGroup optionsRadioGroup;
    private Button prevButton, nextButton, submitButton;
    private static final String TAG = "QuizFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        // Initialize UI components
        questionTextView = view.findViewById(R.id.questionTextView);
        optionsRadioGroup = view.findViewById(R.id.optionsRadioGroup);
        prevButton = view.findViewById(R.id.prevButton);
        nextButton = view.findViewById(R.id.nextButton);
        submitButton = view.findViewById(R.id.submitButton);
        feedbackTextView = view.findViewById(R.id.feedbackTextView);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);

        // Observe loading state
        viewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                Toast.makeText(getContext(), "Loading questions...", Toast.LENGTH_SHORT).show();
            }
        });

        // Observe error messages
        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), error -> {
            if (error != null) {
                Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
            }
        });

        // Observe current question
        viewModel.getCurrentQuestion().observe(getViewLifecycleOwner(), question -> {
            if (question != null) {
                questionTextView.setText(question.getTitle());
                optionsRadioGroup.removeAllViews();
                for (int i = 0; i < question.getAnswers().size(); i++) {
                    QuizAnswer answer = question.getAnswers().get(i);
                    RadioButton radioButton = new RadioButton(getContext());
                    radioButton.setText(answer.getText());
                    radioButton.setId(View.generateViewId());
                    radioButton.setTag(i);
                    optionsRadioGroup.addView(radioButton);
                }
                optionsRadioGroup.clearCheck();
                feedbackTextView.setVisibility(View.GONE);
                updateButtonVisibility();
            }
        });

        // Observe feedback
        viewModel.getFeedback().observe(getViewLifecycleOwner(), feedback -> {
            if (feedback != null) {
                feedbackTextView.setText(feedback);
                feedbackTextView.setVisibility(View.VISIBLE);
            } else {
                feedbackTextView.setVisibility(View.GONE);
            }
        });

        // Observe quiz completion and navigate to ResultActivity with skinType
        viewModel.getIsQuizFinished().observe(getViewLifecycleOwner(), isFinished -> {
            if (isFinished) {
                SkinType skinType = viewModel.getSkinType().getValue();
                if (skinType != null) {
                    Log.i(TAG, "Navigating to ResultActivity with SkinType: " + skinType.getType());
                    Intent intent = new Intent(getActivity(), ResultActivity.class);
                    intent.putExtra("skinType", skinType); // Pass the entire SkinType object
                    startActivity(intent);
                } else {
                    Log.w(TAG, "SkinType is null when quiz finished");
                    Toast.makeText(getContext(), "Error: Skin type not determined", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Handle Submit button click
        submitButton.setOnClickListener(v -> {
            int selectedId = optionsRadioGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(getContext(), "Please select an option", Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton selectedRadioButton = view.findViewById(selectedId);
            int selectedIndex = (int) selectedRadioButton.getTag();
            viewModel.selectAnswer(selectedIndex);
        });

        // Handle Next button click
        nextButton.setOnClickListener(v -> {
            viewModel.nextQuestion();
        });

        // Handle Previous button click
        prevButton.setOnClickListener(v -> {
            viewModel.previousQuestion();
        });

        return view;
    }

    // Update button visibility based on current question index
    private void updateButtonVisibility() {
        int currentIndex = viewModel.getCurrentQuestionIndex();
        int totalQuestions = viewModel.getTotalQuestions();
        prevButton.setVisibility(currentIndex > 0 ? View.VISIBLE : View.GONE);
        nextButton.setVisibility(currentIndex < totalQuestions - 1 ? View.VISIBLE : View.VISIBLE);
        submitButton.setVisibility(View.VISIBLE);
    }
}