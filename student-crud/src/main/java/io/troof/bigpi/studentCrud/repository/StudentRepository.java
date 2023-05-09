package io.troof.bigpi.studentCrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.troof.bigpi.studentCrud.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
}
