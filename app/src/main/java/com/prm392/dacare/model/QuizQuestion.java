package com.prm392.dacare.model;

import java.util.List;

import lombok.Getter;

public class QuizQuestion {
    private String _id;
    @Getter
    private String title;
    @Getter
    private String description;
    @Getter
    private List<QuizAnswer> answers;

    // Constructor (optional, if needed for initialization)
    public QuizQuestion(String _id, String title, String description, List<QuizAnswer> answers) {
        this._id = _id;
        this.title = title;
        this.description = description;
        this.answers = answers;
    }

    // Getters
    public String getId() {
        return _id;
    }

}
