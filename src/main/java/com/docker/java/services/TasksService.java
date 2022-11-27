package com.docker.java.services;

import com.docker.java.DTOs.TaskDTO;
import com.docker.java.entities.Task;
import com.docker.java.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TasksService {
    @Autowired
    private TasksRepository repository;

    public TaskDTO create(TaskDTO taskDTO) {

        Task task = new Task();

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());

        repository.save(task);

        taskDTO.setId(task.getId());

        return taskDTO;
    }

    public TaskDTO update(TaskDTO taskDTO) {
        Task task = repository.getById(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());

        return taskDTO;

    }

    private TaskDTO convert(Task task) {
        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());

        return taskDTO;
    }

    public List<TaskDTO> get() {
        return repository.findAll().stream().map(this::convert).collect(Collectors.toList());
    }

    public String delete(Long id) {
        repository.deleteById(id);

        return "Task deleted with success";
    }
}
