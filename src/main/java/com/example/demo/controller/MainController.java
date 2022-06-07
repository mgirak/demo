package com.example.demo.controller;

import com.example.demo.service.DiplomaServiceImpl;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  @Autowired
  private DiplomaServiceImpl diplomaService;

  @GetMapping("/main")
  public String mainPage(@RequestParam(required = false, defaultValue = "") String filter,
      Model model) throws IOException {
    model.addAttribute("diplomas", diplomaService.findByAnyField(filter))
        .addAttribute("filter", filter);
    return "main";
  }
}