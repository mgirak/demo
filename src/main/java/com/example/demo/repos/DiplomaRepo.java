package com.example.demo.repos;

import com.example.demo.domain.Diploma;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiplomaRepo extends CrudRepository<Diploma, Integer> {
  List<Diploma> findAllByTitleContainsOrDirectionContainsOrDirectorContainsOrStudentContains(
      String title, String direction, String director, String student);

  List<Diploma> findByTitle(int id);
}