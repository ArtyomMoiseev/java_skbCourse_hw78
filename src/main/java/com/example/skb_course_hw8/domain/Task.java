package com.example.skb_course_hw8.domain;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Task() {

    }

    public Task(String taskName) {
        this.taskName = taskName;
    }

    @NotBlank(message = "task is empty")
    @Size(min = 1, max = 250, message = "message length incorrect")
    @Expose
    private String taskName;

    @Override
    public String toString() {
        return taskName;
    }

}
