package com.example.skb_course_hw8.repo;

import com.example.skb_course_hw8.domain.ToDoList;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<ToDoList, Integer> {
}
