package com.example.interview.services;
import com.example.interview.models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface StudentService extends CrudRepository<Student, Integer> {
}
