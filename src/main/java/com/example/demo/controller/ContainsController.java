package com.example.demo.controller;

import com.example.demo.service.DiplomaServiceImpl;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContainsController {

  @Autowired
  private DiplomaServiceImpl diplomaService;

  @GetMapping("/searchContains")
  public String mainPage(@RequestParam(required = false, defaultValue = "") String filter,
      Model model) throws IOException {
    model.addAttribute("diplomas", diplomaService.findAllByContainsInText(filter))
        .addAttribute("filter", filter);
    return "searchContains";
  }
}