package com.example.MVCApp.exercise;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Controller
public class ExerciseController {

    @Autowired
    ExerciseRepository exerciseRepository;
    @Autowired
    ExerciseService service;

    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }

    @GetMapping("/exercise-list")
    public String exerciseList(
            Model model,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        try{
            Page<Exercise> data = service.getPagedExercises(pageNo, pageSize, sortBy);
            model.addAttribute("data", data);
            model.addAttribute("status", true);
            System.out.print(model);
        } catch(Exception ex) {
            model.addAttribute("message", "No exercises found.");
            model.addAttribute("status", false);
        }
        return "exercise-list-page";
    }

    @PostMapping("/upload-exercise-csv")
    public String uploadExerciseCSV(@RequestParam("file")MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
                CsvToBean<Exercise> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Exercise.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
                List<Exercise> exercises = csvToBean.parse();

                exerciseRepository.saveAll(exercises);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }
        return "redirect:exercise-list";
    }

}