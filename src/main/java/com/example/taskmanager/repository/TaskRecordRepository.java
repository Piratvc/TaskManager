package com.example.taskmanager.repository;
import com.example.taskmanager.model.TaskRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface TaskRecordRepository extends JpaRepository<TaskRecord, Long> {
    List<TaskRecord> findByUserId(Long id);
    List<TaskRecord> findByStatusId(Long id);
    List<TaskRecord> findByName(String name);
//    List<TaskRecord> findByTimeAndUserId(Date time, Long id);
}
