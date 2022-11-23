package com.example.taskmanager.controller;

import com.example.taskmanager.model.Status;
import com.example.taskmanager.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StatusController {
    @Autowired
    private final StatusService statusService;
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }
    @GetMapping("/statuses")
    public String findAll(Model model) {
        List<Status> statusList = statusService.findAll();
        model.addAttribute("statuses", statusList);
        return "status-list";
    }

    @GetMapping("/status-create")
    public String createStatusForm(Status status) {
        return "status-create";
    }

    @PostMapping("/status-create")
    public String createStatus(Status status) {
        statusService.saveStatus(status);
        return "redirect:/statuses";
    }

    @GetMapping("status-delete/{id}")
    public String deleteStatus(@PathVariable("id") Long id) {
        statusService.deleteById(id);
        return "redirect:/statuses";
    }

    @GetMapping("/status-update/{id}")
    public String updateStatusForm(@PathVariable("id") Long id, Model model) {
        Status status = statusService.findById(id);
        model.addAttribute("status", status);
        return "status-update";
    }

    @PostMapping("/status-update")
    public String updateStatus(Status status) {
        statusService.saveStatus(status);
        return "redirect:/statuses";
    }
}
