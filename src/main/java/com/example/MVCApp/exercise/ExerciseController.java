package com.example.MVCApp.exercise;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Locale;

@Controller
public class ExerciseController {

    @Autowired
    ExerciseRepository exerciseRepository;
    @Autowired
    ExerciseService service;

    @GetMapping("/")
    public String index(){
        return "redirect:exercise-list";
    }

    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }
    @GetMapping("/create-form")
    public String createForm(){
        return "create-exercise-form";
    }

    @PostMapping("/create-exercise")
    public String createExercise(@ModelAttribute("Exercise") Exercise form, Model model){
        exerciseRepository.save(
                new Exercise(
                        form.getName(),
                        form.getCategory(),
                        form.getClassification(),
                        form.getPrimaryAction(),
                        form.getSecondaryAction1(),
                        form.getSecondaryAction2(),
                        form.getPrimaryMuscle(),
                        form.getSynergist1(),
                        form.getSynergist2(),
                        form.getEquipment1(),
                        form.getEquipment2(),
                        form.getMedia(),
                        form.getInstructions()
                ));
        return "redirect:exercise-list";
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

//                I'd recommend trying something non-blocking here, then the user can submit the csv and immediately
//                  be redirected while the controller saves the exercises behind the scenes
//                This is a hack attempt at saving them in a thread (runs async) but it locked up the db
//                A better option would be to utilise Flux and Mono: https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html
//                Runnable r = new Runnable() {
//                    @Override
//                    public void run() {
//                        exercises.forEach(exerciseRepository::save);
//                    }
//                };
//                Thread t = new Thread(r);
//                t.start();
                exerciseRepository.saveAll(exercises);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }
        return "redirect:exercise-list";
    }

}