package com.prm392.dacare.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prm392.dacare.model.QuizAnswer;
import com.prm392.dacare.model.QuizQuestion;
import com.prm392.dacare.model.SkinType;
import com.prm392.dacare.payload.response.QuizResultResponse;
import com.prm392.dacare.repository.QuizQuestionRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizViewModel extends ViewModel {
    private final QuizQuestionRepository repository;
    private List<QuizQuestion> questions;
    private int currentQuestionIndex = 0;
    private int totalPoints = 0;

    private final MutableLiveData<QuizQuestion> currentQuestion = new MutableLiveData<>();
    private final MutableLiveData<String> feedback = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isQuizFinished = new MutableLiveData<>(false);
    private final MutableLiveData<SkinType> skinType = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public QuizViewModel() {
        repository = new QuizQuestionRepository();
        fetchQuizQuestions();
    }

    // Public getters for LiveData
    public LiveData<QuizQuestion> getCurrentQuestion() { return currentQuestion; }
    public LiveData<String> getFeedback() { return feedback; }
    public LiveData<Boolean> getIsQuizFinished() { return isQuizFinished; }
    public LiveData<SkinType> getSkinType() { return skinType; }
    public LiveData<Boolean> getIsLoading() { return isLoading; }
    public LiveData<String> getErrorMessage() { return errorMessage; }

    // Additional getters for navigation
    public int getCurrentQuestionIndex() { return currentQuestionIndex; }
    public int getTotalQuestions() { return questions != null ? questions.size() : 0; }

    // Fetch quiz questions
    private void fetchQuizQuestions() {
        isLoading.setValue(true);
        repository.getQuizQuestions(new Callback<List<QuizQuestion>>() {
            @Override
            public void onResponse(Call<List<QuizQuestion>> call, Response<List<QuizQuestion>> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    questions = response.body();
                    currentQuestion.setValue(questions.get(0));
                } else {
                    errorMessage.setValue("No questions available");
                }
            }

            @Override
            public void onFailure(Call<List<QuizQuestion>> call, Throwable t) {
                isLoading.setValue(false);
                errorMessage.setValue("Network error: " + t.getMessage());
            }
        });
    }

    // Handle answer selection
    public void selectAnswer(int selectedIndex) {
        QuizQuestion question = currentQuestion.getValue();
        if (question != null) {
            QuizAnswer selectedAnswer = question.getAnswers().get(selectedIndex);
            try {
                int points = Integer.parseInt(selectedAnswer.getPoint());
                totalPoints += points;
                feedback.setValue("Answer recorded. Points: " + points);
            } catch (NumberFormatException e) {
                feedback.setValue("Invalid point value");
            }
        }
    }

    // Move to next question or finish quiz
    public void nextQuestion() {
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            currentQuestion.setValue(questions.get(currentQuestionIndex));
            feedback.setValue(null);
        } else {
            performSkinAnalysis();
        }
    }

    // Move to previous question
    public void previousQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            currentQuestion.setValue(questions.get(currentQuestionIndex));
            feedback.setValue(null);
        }
    }

    // Call skin analysis API
    private void performSkinAnalysis() {
        isLoading.setValue(true);
        repository.skinAnalysis(new Callback<QuizResultResponse>() {
            @Override
            public void onResponse(Call<QuizResultResponse> call, Response<QuizResultResponse> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    QuizResultResponse result = response.body();
                    skinType.setValue(result.getData().getSkinType());
                    isQuizFinished.setValue(true);
                } else {
                    errorMessage.setValue("Failed to analyze skin type");
                }
            }

            @Override
            public void onFailure(Call<QuizResultResponse> call, Throwable t) {
                isLoading.setValue(false);
                errorMessage.setValue("Network error: " + t.getMessage());
            }
        }, totalPoints);
    }
}