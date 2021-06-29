package com.example.MVCApp.exercise;

import javax.persistence.*;

/**
 * AD: have a look at Lombok library: https://projectlombok.org/setup/maven
 * the getter/setter methods here are "boilerplate" in that you'll need to re-write them for every class even though they
 * don't do anything special
 * Lombok abstracts this and will create the methods at runtime so you only need to declare variables could reduce this
 * class to ~40 Lines
 *
 */
@Entity
@Table
public class Exercise {

    @Id
    @SequenceGenerator(
            name= "exercise_sequence",
            sequenceName = "exercise_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "exercise_sequence"
    )
    private long id;

    private String name;
    private String category;
    private String classification;
    private String primaryAction;
    private String secondaryAction1;
    private String secondaryAction2;
    private String primaryMuscle;
    private String synergist1;
    private String synergist2;
    private String equipment1;
    private String equipment2;
    private String media;
    private String instructions;

    public Exercise() {

    }

    public Exercise(long id,
                    String name,
                    String category,
                    String classification,
                    String primaryAction,
                    String secondaryAction1,
                    String secondaryAction2,
                    String primaryMuscle,
                    String synergist1,
                    String synergist2,
                    String equipment1,
                    String equipment2,
                    String media,
                    String instructions) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.classification = classification;
        this.primaryAction = primaryAction;
        this.secondaryAction1 = secondaryAction1;
        this.secondaryAction2 = secondaryAction2;
        this.primaryMuscle = primaryMuscle;
        this.synergist1 = synergist1;
        this.synergist2 = synergist2;
        this.equipment1 = equipment1;
        this.equipment2 = equipment2;
        this.media = media;
        this.instructions = instructions;
    }

    public Exercise(String name,
                    String category,
                    String classification,
                    String primaryAction,
                    String secondaryAction1,
                    String secondaryAction2,
                    String primaryMuscle,
                    String synergist1,
                    String synergist2,
                    String equipment1,
                    String equipment2,
                    String media,
                    String instructions) {
        this.name = name;
        this.category = category;
        this.classification = classification;
        this.primaryAction = primaryAction;
        this.secondaryAction1 = secondaryAction1;
        this.secondaryAction2 = secondaryAction2;
        this.primaryMuscle = primaryMuscle;
        this.synergist1 = synergist1;
        this.synergist2 = synergist2;
        this.equipment1 = equipment1;
        this.equipment2 = equipment2;
        this.media = media;
        this.instructions = instructions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getPrimaryAction() {
        return primaryAction;
    }

    public void setPrimaryAction(String primaryAction) {
        this.primaryAction = primaryAction;
    }

    public String getSecondaryAction1() {
        return secondaryAction1;
    }

    public void setSecondaryAction1(String secondaryAction1) {
        this.secondaryAction1 = secondaryAction1;
    }

    public String getSecondaryAction2() {
        return secondaryAction2;
    }

    public void setSecondaryAction2(String secondaryAction2) {
        this.secondaryAction2 = secondaryAction2;
    }

    public String getPrimaryMuscle() {
        return primaryMuscle;
    }

    public void setPrimaryMuscle(String primaryMuscle) {
        this.primaryMuscle = primaryMuscle;
    }

    public String getSynergist1() {
        return synergist1;
    }

    public void setSynergist1(String synergist1) {
        this.synergist1 = synergist1;
    }

    public String getSynergist2() {
        return synergist2;
    }

    public void setSynergist2(String synergist2) {
        this.synergist2 = synergist2;
    }

    public String getEquipment1() {
        return equipment1;
    }

    public void setEquipment1(String equipment1) {
        this.equipment1 = equipment1;
    }

    public String getEquipment2() {
        return equipment2;
    }

    public void setEquipment2(String equipment2) {
        this.equipment2 = equipment2;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", classification='" + classification + '\'' +
                ", primaryAction='" + primaryAction + '\'' +
                ", secondaryAction1='" + secondaryAction1 + '\'' +
                ", secondaryAction2='" + secondaryAction2 + '\'' +
                ", primaryMuscle='" + primaryMuscle + '\'' +
                ", synergist1='" + synergist1 + '\'' +
                ", synergist2='" + synergist2 + '\'' +
                ", equipment1='" + equipment1 + '\'' +
                ", equipment2='" + equipment2 + '\'' +
                ", media='" + media + '\'' +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}