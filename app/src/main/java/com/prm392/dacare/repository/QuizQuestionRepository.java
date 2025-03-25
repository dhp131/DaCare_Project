package com.prm392.dacare.repository;

import android.util.Log;

import com.prm392.dacare.model.QuizQuestion;
import com.prm392.dacare.payload.request.QuizResultRequest;
import com.prm392.dacare.payload.response.QuizResultResponse;
import com.prm392.dacare.service.APIClient;
import com.prm392.dacare.service.APIService;

import java.util.List;
import retrofit2.Callback;

public class QuizQuestionRepository {
    private APIService apiService;
    public QuizQuestionRepository() {
        apiService = APIClient.getClient().create(APIService.class);
    }

    public void getQuizQuestions(Callback<List<QuizQuestion>> callback) {
        Log.i("QuizQuestionRepository: ", "QuizQuestionRepository: getQuizQuestions");
        apiService.getQuizQuestions().enqueue(callback);
    }
    public void skinAnalysis(Callback<QuizResultResponse> callback, Integer points) {
        apiService.skinAnalysis(new QuizResultRequest(points)).enqueue(callback);
    }
}
