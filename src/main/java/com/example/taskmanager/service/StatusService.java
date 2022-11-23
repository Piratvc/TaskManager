package com.example.taskmanager.service;

import com.example.taskmanager.model.Status;
import com.example.taskmanager.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StatusService {
    @Autowired
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Status findById(Long id) {
        return statusRepository.getReferenceById(id);
    }

    public void saveStatus(Status status) {
        statusRepository.save(status);
    }

    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    public void deleteById(Long id) {
        statusRepository.deleteById(id);
    }

}
