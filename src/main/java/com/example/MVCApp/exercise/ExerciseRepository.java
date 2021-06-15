package com.example.MVCApp.exercise;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "exercises", path="exercise")
public interface ExerciseRepository extends PagingAndSortingRepository <Exercise, Long> {

}
