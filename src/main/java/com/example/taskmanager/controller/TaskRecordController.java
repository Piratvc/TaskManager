package com.example.taskmanager.controller;

import com.example.taskmanager.model.Status;
import com.example.taskmanager.model.TaskRecord;
import com.example.taskmanager.service.TaskRecordService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

@Controller
public class TaskRecordController {
    @Autowired
    private final TaskRecordService taskRecordService;
    public TaskRecordController(TaskRecordService taskRecordService) {
        this.taskRecordService = taskRecordService;
    }
    @GetMapping("/records")
    public String findAll(Model model) {
        List<TaskRecord> records = taskRecordService.findAll();
        model.addAttribute("records", records);
        return "records-list";
    }

    @GetMapping("/record-create")
    public String createRecordForm(TaskRecord record) {
        return "record-create";
    }

    @PostMapping("/record-create")
    public String createRecord(TaskRecord record) throws InterruptedException {
        Status status = new Status();
        status.setId(1L);
        status.setName("RENDERING");
        record.setStatus(status);
        record.setTime(new Date(Calendar.getInstance().getTimeInMillis()));
        taskRecordService.saveRecord(record);
        //здесь для реализации обновления статуса задачи открываю парралельный поток,
        // в котором отсчитываю рандомно чуть больше 30 секунд
        // и создаю новую запись что задача завершена
        Thread myThread = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep((long) (1000 * (Math.random()*10 + 30)));
                TaskRecord taskRecord = new TaskRecord();
                status.setId(2L);
                status.setName("COMPLETE");
                taskRecord.setStatus(status);
                taskRecord.setUser(record.getUser());
                taskRecord.setName(record.getName());
                taskRecord.setTime(new Date(Calendar.getInstance().getTimeInMillis()));
                taskRecordService.saveRecord(taskRecord);
            }
        });
        myThread.start();
        return "redirect:/records";
    }

    @GetMapping("record-delete/{id}")
    public String deleteRecord(@PathVariable("id") Long id) {
        taskRecordService.deleteById(id);
        return "redirect:/records";
    }

    @GetMapping("/record-update/{id}")
    public String updateRecordForm(@PathVariable("id") Long id, Model model) {
        TaskRecord record = taskRecordService.findById(id);
        model.addAttribute("taskRecord", record);
        return "record-update";
    }

    @PostMapping("/record-update")
    public String updateRecord(TaskRecord record) throws InterruptedException {
        taskRecordService.saveRecord(record);
        return "redirect:/records";
    }

    @GetMapping("/records/user-{id}/")
    public String findTaskRecordsByUserId(@PathVariable("id") Long id, Model model) {
        List<TaskRecord> records = taskRecordService.getTaskRecordsByUserId(id);
        model.addAttribute("records", records);
        return "records-personal";

    }

    @GetMapping("/records/{name}")
    public String findTaskRecordsByTaskRecordName(@PathVariable("name") String name, Model model) {
        List<TaskRecord> records = taskRecordService.getTaskRecordsByTaskRecordsName(name);
        model.addAttribute("records", records);
        return "records-taskName";
    }

    @GetMapping("/records/status-{id}/")
    public String findTaskRecordsByStatusId(@PathVariable("id") Long id, Model model) {
        List<TaskRecord> records = taskRecordService.getTaskRecordsByStatusId(id);
        model.addAttribute("records", records);
        return "records-status";
    }
}
