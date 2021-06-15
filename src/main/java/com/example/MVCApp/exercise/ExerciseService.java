package com.example.MVCApp.exercise;

import com.example.MVCApp.exercise.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseService {
    @Autowired
    ExerciseRepository repository;

    public Page<Exercise> getPagedExercises(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable pageOptions = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Exercise> results = repository.findAll(pageOptions);

        if(results.hasContent()) {
            return results;
        } else {
            return null;
        }
    }

}