package com.example.taskmanager.service;
import com.example.taskmanager.model.TaskRecord;
import com.example.taskmanager.repository.TaskRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskRecordService {
    @Autowired
    private final TaskRecordRepository taskRecordRepository;

    public TaskRecordService(TaskRecordRepository taskRecordRepository) {
        this.taskRecordRepository = taskRecordRepository;
    }

    public TaskRecord findById(Long id) {
        return taskRecordRepository.getReferenceById(id);
    }

    public void saveRecord(TaskRecord taskRecord) {
        taskRecordRepository.save(taskRecord);
    }

    public List<TaskRecord> findAll() {
        return taskRecordRepository.findAll();
    }

    public void deleteById(Long id) {
        taskRecordRepository.deleteById(id);
    }

    public List<TaskRecord> getTaskRecordsByUserId(Long id) {
        return taskRecordRepository.findByUserId(id);
    }

    public List<TaskRecord> getTaskRecordsByStatusId(Long id) {
        return taskRecordRepository.findByStatusId(id);
    }

    public List<TaskRecord> getTaskRecordsByTaskRecordsName(String name) {
        return taskRecordRepository.findByName(name);
    }
}


