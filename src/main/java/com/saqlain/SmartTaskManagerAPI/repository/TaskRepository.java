package com.saqlain.SmartTaskManagerAPI.repository;

import com.saqlain.SmartTaskManagerAPI.entity.Tasks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Long> {

    List<Tasks> findAllByUserEmail(String email);

    Page<Tasks> findAllByUserEmail(String email, Pageable pageable);

    Optional<Tasks> getTaskByIdAndUserEmail(Long id, String email);

    List<Tasks> findTaskByUserEmailAndStatus(String email, String status);

    List<Tasks> findTaskByUserEmailAndPriority(String email, String priority);

    List<Tasks> findTaskByUserEmailAndCategoryName(String email, String category);

    List<Tasks> findTaskByUserEmailAndTitleContainingIgnoreCase(String email, String title);
}
