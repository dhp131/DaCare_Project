package com.prm392.dacare.model;

import lombok.Getter;

public class QuizAnswer {
    private String _id;
    @Getter
    private String text;
    @Getter
    private String point;

    // Constructor (optional, if needed for initialization)
    public QuizAnswer(String _id, String text, String point) {
        this._id = _id;
        this.text = text;
        this.point = point;
    }

    // Getters
    public String getId() {
        return _id;
    }

}
