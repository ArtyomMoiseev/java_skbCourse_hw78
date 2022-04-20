package com.example.skb_course_hw8.controllers;

import com.example.skb_course_hw8.domain.Task;
import com.example.skb_course_hw8.domain.ToDoList;
import com.example.skb_course_hw8.repo.ToDoRepository;
import com.example.skb_course_hw8.serializers.ToDoListSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ToDoListController {

    private final ToDoRepository toDoRepository;

    public ToDoListController(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @PostMapping(value = "/saveList")
    public ResponseEntity<String> saveList(@RequestBody String body) throws JSONException {
        var json = new JSONObject(body);
        var events = new ArrayList<Task>();
        for (var t: json.getJSONArray("events").toList()) {
            events.add(new Task(t.toString()));
        }
        var toDoList = new ToDoList(json.get("name").toString(), events);
        toDoRepository.save(toDoList);
        return new ResponseEntity<String>("Successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/getList")
    public ResponseEntity<String> getList() {
        var gson = new GsonBuilder().registerTypeAdapter(ToDoList.class, new ToDoListSerializer())
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        var lists = toDoRepository.findAll();
        var result = gson.toJson(lists);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/clear")
    public ResponseEntity<String> clear() {
        toDoRepository.deleteAll();
        return new ResponseEntity<String>("Successfully", HttpStatus.OK);
    }

}
