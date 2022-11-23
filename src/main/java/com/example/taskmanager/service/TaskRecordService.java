package com.example.taskmanager.service;

import com.example.taskmanager.model.Status;
import com.example.taskmanager.model.TaskRecord;
import com.example.taskmanager.repository.TaskRecordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//import java.sql.Date;
import java.util.Date;
import java.util.Calendar;
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

    public void saveRecord(TaskRecord taskRecord) throws InterruptedException {
//        Status status = new Status();
//        status.setId(2L);
//        status.setName("RENDERING");
//        taskRecord.setStatus(status);
//        taskRecord.setTime(new Date(Calendar.getInstance().getTime().getTime()));
//        System.out.println(taskRecord);
        taskRecordRepository.save(taskRecord);
        //реализация задержки в 5 минут для сохранения задачи с новым статусом
//        Thread.sleep(2000);
//
//        status.setId(1L);
//        status.setName("COMPLETE");
//        taskRecord.setStatus(status);
//        taskRecord.setTime(new Date(Calendar.getInstance().getTime().getTime()));
//        taskRecordRepository.save(taskRecord);
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


