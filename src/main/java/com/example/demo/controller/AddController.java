package com.example.demo.controller;

import com.example.demo.service.DiplomaServiceImpl;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AddController {

  @Autowired
  private DiplomaServiceImpl diplomaService;

  @GetMapping("/add")
  public String addingPage(Model model) {
    model.addAttribute("diplomas", diplomaService.findAll());
    return "add";
  }

  @PostMapping("/add")
  public String add(@RequestParam(required = false, name = "file") MultipartFile file,
      Model model) throws IOException {
      diplomaService.add(file, model);
    model.addAttribute("diplomas", diplomaService.findAll());
    return "add";
  }

  @Transactional
  @GetMapping("/delete/{id}")
  public String delete(@PathVariable("id") int id) {
    diplomaService.delete(id);
    return "redirect:/add";
  }
}