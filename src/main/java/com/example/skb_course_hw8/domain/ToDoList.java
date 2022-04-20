package com.example.skb_course_hw8.domain;


import com.google.gson.*;
import com.google.gson.annotations.Expose;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.Array;
import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@Entity
public class ToDoList implements JsonSerializer<Boolean> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public ToDoList() {

    }

    public ToDoList(String name, List<Task> tasks) {
        this.listName = name;
        this.taskList = tasks;
    }


    @NotBlank(message = "list name can't be blank")
    @Size(min = 1, max = 120, message = "list name have incorrect length")
    @Expose
    private String listName;


    @OneToMany(cascade = CascadeType.ALL)
    @Expose
    private List<Task> taskList;


    public String getListName() {
        return listName;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    @Override
    public JsonElement serialize(Boolean aBoolean, Type type, JsonSerializationContext jsonSerializationContext) {
        var json = new JsonObject();
        json.addProperty("name", listName);
        json.addProperty("events", taskList.toString());
        return json;
    }
}
