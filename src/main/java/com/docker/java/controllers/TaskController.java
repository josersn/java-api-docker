package com.docker.java.controllers;

import com.docker.java.DTOs.TaskDTO;
import com.docker.java.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

    @Autowired
    private TasksService service;

    @PostMapping
    @ResponseBody
    public TaskDTO create(@RequestBody TaskDTO taskDTO) {
        return service.create(taskDTO);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public TaskDTO update(@PathVariable("id") Long id,
                               @RequestBody TaskDTO task) {
        return service.update(task);
    }

    @GetMapping
    @ResponseBody
    public List<TaskDTO> list() {
        return service.get();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
