package com.example.skb_course_hw8.serializers;

import com.example.skb_course_hw8.domain.ToDoList;
import com.google.gson.*;

import java.lang.reflect.Type;

public class ToDoListSerializer implements JsonSerializer<ToDoList> {
    @Override
    public JsonElement serialize(ToDoList toDoList, Type type, JsonSerializationContext jsonSerializationContext) {
        var json = new JsonObject();
        json.addProperty("name", toDoList.getListName());
        var taskNames = new JsonArray();
        for (var taskName: toDoList.getTaskList()) {
            taskNames.add(taskName.toString());
        }
        json.add("events", taskNames);
        return json;
    }
}
