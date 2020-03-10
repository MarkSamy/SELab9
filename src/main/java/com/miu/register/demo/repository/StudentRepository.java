package com.miu.register.demo.repository;

import com.miu.register.demo.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE CONCAT(s.id, ' '" +
            ", s.studentNumber,' '" +
            ",s.firstName,' '" +
            ",s.middleName,' '" +
            ",s.lastName,' '," +
            "s.cgpa,' '," +
            "s.dateOfEnrollment,' '," +
            "s.isInternational,' ' ) LIKE CONCAT('%', :search, '%')")
    List<Student> searchStudents(@Param("search") String search);
}
