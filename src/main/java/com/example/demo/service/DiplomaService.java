package com.example.demo.service;

import com.example.demo.domain.Diploma;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface DiplomaService {

  void add(MultipartFile file, Model model) throws IOException;

  void delete(int id);

  Iterable<Diploma> findAll();

  Iterable<Diploma> findByAnyField(String title);

  ArrayList<Diploma> findAllByContainsInText(String filter) throws IOException;
}