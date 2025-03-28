package com.prm392.dacare.payload.response;

import com.prm392.dacare.model.SkinType;

import lombok.Getter;

@Getter
public class QuizResultResponse {
    private Data data;

    @Getter
    public static class Data {
        private SkinType skinType;

        public void setSkinType(SkinType skinType) { this.skinType = skinType; }
        // Other getters and setters omitted for brevity
    }
}
