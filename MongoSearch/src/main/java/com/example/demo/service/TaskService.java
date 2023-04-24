package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	// CRUD
	
	public Task addTask(Task task) {
		System.out.println("enter in addTask method : ");
		task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
		return taskRepository.save(task);
	}
	
	public List<Task> findAllTask(){
		return taskRepository.findAll();
	}
	
	public Task findById(String taskId) {
		return taskRepository.findById(taskId).get();
	}
	
	public List<Task> findBySeverity(int severity) {
		return taskRepository.findBySeverity(severity);
	}
	
	// Try to write custom query
	public List<Task> findByAssignee(String assignee) {
		return taskRepository.findByAssignee(assignee);
	}
	
	public Task updateTask(Task taskRequest) {
		// get the exist data from db then update that
		Task existingTask = taskRepository.findById(taskRequest.getTaskId()).get();
		existingTask.setAssignee(taskRequest.getAssignee());
		existingTask.setDescription(taskRequest.getDescription());
		existingTask.setStoryPoint(taskRequest.getStoryPoint());
		existingTask.setSeverity(taskRequest.getSeverity());
		return taskRepository.save(existingTask);
	}
	
	public String deleteTask(String taskId) {
		taskRepository.deleteById(taskId);
		return "Task Id : "+taskId;
	}
	
}
