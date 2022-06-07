package com.example.demo.service;

import com.example.demo.domain.Diploma;
import com.example.demo.pdfParser.PdfParser;
import com.example.demo.repos.DiplomaRepo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DiplomaServiceImpl implements DiplomaService {

  @Autowired
  private DiplomaRepo diplomaRepo;

  @Autowired
  private PdfParser parser;

  @Value("${upload.path}")
  private String uploadPath;

  @Override
  public void add(MultipartFile file, Model model) throws IOException {
    if (!file.isEmpty()) {
      var fileName = file.getOriginalFilename();
      file.transferTo(new File(uploadPath + "/" + fileName));
      var diploma = parser.getDiplomaFromFile(fileName);
      diploma.setFilename(fileName);
      diplomaRepo.save(diploma);
    }
  }

  @Override
  public Iterable<Diploma> findByAnyField(String filter) {
    if (filter != null && !filter.isEmpty()) {
      return diplomaRepo.findAllByTitleContainsOrDirectionContainsOrDirectorContainsOrStudentContains(
          filter, filter, filter, filter);
    } else {
      return findAll();
    }
  }

  @Override
  public ArrayList<Diploma> findAllByContainsInText(String filter) throws IOException {
    if (filter!= null && !filter.isEmpty()) {
      return parser.lookFor(filter);
    }else {
      return (ArrayList<Diploma>) findAll();
    }
  }

  @Override
  public void delete(int id) {
    diplomaRepo.deleteById(id);
    var getted = diplomaRepo.findById(id).get().getFilename();
    var file = new File(uploadPath + "/" + getted);
    if (file.exists()) {
      file.delete();
    }

  }

  @Override
  public Iterable<Diploma> findAll() {
    return diplomaRepo.findAll();
  }
}