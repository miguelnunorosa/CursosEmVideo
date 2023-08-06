package io.github.miguelnunorosa.applistavip.controller;

import java.util.ArrayList;
import java.util.List;

import io.github.miguelnunorosa.applistavip.model.Curso;

public class CursoController {

    private List listCourses;




    public List getCoursList(){
         listCourses = new ArrayList<Curso>();

         listCourses.add(new Curso("Java")); //ex: Java
         listCourses.add(new Curso("Android")); //ex: Android
         listCourses.add(new Curso("C#")); //ex: C#
         listCourses.add(new Curso("Python")); //ex: Python
         listCourses.add(new Curso("PHP"));
         listCourses.add(new Curso("Java EE"));
         listCourses.add(new Curso("Flutter"));
         listCourses.add(new Curso("Dart"));

         return listCourses;
    }

}
