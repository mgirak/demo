package com.example.demo.pdfParser;

import com.example.demo.domain.Diploma;
import com.example.demo.service.DiplomaServiceImpl;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PdfParser {
  @Autowired
  private DiplomaServiceImpl diplomaService;

  private static final String path = "D:\\diplomWork\\uploadedFiles\\";

  public String configureReader(String fileName) throws IOException {
    try (var doc = PDDocument.load(new File(path + fileName))) {
      var reader = new PDFTextStripper();
      reader.setEndPage(1);
      return reader.getText(doc);
    }
  }

  public String readWhole(String fileName) throws IOException{
    try (var doc = PDDocument.load(new File(path + fileName))) {
      var reader = new PDFTextStripper();
     return reader.getText(doc);
    }
  }

  public ArrayList<Diploma> lookFor(String filter) throws IOException{
    var all = diplomaService.findAll();
    var result = new ArrayList<Diploma>();
    for (Diploma d: all){
      if (readWhole(d.getFilename()).contains(filter)){
        result.add(d);
      }
    }
    return result;
  }

  public Diploma getDiplomaFromFile(String fileName) throws IOException {
    var parsedString = configureReader(fileName);
    return new Diploma(getTypeOfWork(parsedString), getTitle(parsedString),
        getGroup(parsedString), getStudent(parsedString),
        getDirector(parsedString));
  }

  public String getTitle(String parsedString) throws IOException {
    var splitedByLineBreak = parsedString.substring(parsedString.indexOf("УТВЕРЖДАЮ"),
        parsedString.indexOf(
            parsedString.contains("Выпускная квалификационная работа") ? "Выпускная"
                : "Курсовой проект")).split("\n");
    var nameOfDiploma = new StringBuilder();
    for (int i = 4; i > 0; i--) {
      nameOfDiploma.append(splitedByLineBreak[splitedByLineBreak.length - i].replaceAll("\r", "")
          .replaceAll("  ", " "));
    }
    return new String(nameOfDiploma).trim();
  }

  public String getGroup(String parsedString) throws IOException {
    var splitedByLineBreak = parsedString.substring(parsedString.indexOf("Выполнил")).split("\n");
    var splitedBySpace = splitedByLineBreak[1].split(" ");
    return splitedBySpace[splitedBySpace.length - 2].trim();
  }

  public String getDirector(String parsedString) throws IOException {
    var splitedByLineBreak = parsedString.substring(parsedString.indexOf("Научный руководитель"),
        parsedString.indexOf("Выполнил")).split("\n");
    var splitedByUnderLine = splitedByLineBreak[2].split("_");
    return splitedByUnderLine[splitedByUnderLine.length - 1].trim();

  }

  public String getStudent(String parsedString) throws IOException {
    var splitedByLineBreak = parsedString.substring(parsedString.indexOf("Выполнил")).split("\n");
    var splitedByUnderLine = splitedByLineBreak[2].split("_");
    return splitedByUnderLine[splitedByUnderLine.length - 1].trim();
  }

  public String getTypeOfWork(String parsedString) throws IOException {
    return parsedString.contains("Выпускная квалификационная работа") ? "ВКР" : "Курсовой проект";
  }
}