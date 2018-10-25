package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface ListRepository extends CrudRepository<TodoList,Long> {
}
