package com.prm392.dacare.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class SkinType implements Serializable {
    @SerializedName("_id")
    private String _id;
    @Setter
    @Getter
    private String type;
    @Setter
    @Getter
    private String description;
    @Setter
    @Getter
    private String routines;
    @Setter
    @Getter
    private int pointMin;
    @Setter
    @Getter
    private int pointMax;
    @Setter
    @Getter
    private String cause;
    @Setter
    @Getter
    private String symptom;
    @Setter
    @Getter
    private String treatment;

    // Getters and setters
    public String getId() { return _id; }
    public void setId(String id) { this._id = id; }

}