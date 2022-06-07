package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "diplomas")
public class Diploma {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String type;
  private String title;
  private String direction;
  private String student;
  private String director;
  private String filename;

  public Diploma(String type, String title, String direction, String student,
      String director) {
    this.type = type;
    this.title = title;
    this.direction = direction;
    this.student = student;
    this.director = director;
  }
}