package io.github.miguelnunorosa.applistavip.controller;

import java.util.ArrayList;
import java.util.List;

import io.github.miguelnunorosa.applistavip.model.Curso;

public class CursoController {

    public List listCourses;




    public List getCoursList(){
         listCourses = new ArrayList<Curso>();

         listCourses.add(new Curso()); //ex: Java
         listCourses.add(new Curso()); //ex: Android
         listCourses.add(new Curso()); //ex: C#
         listCourses.add(new Curso()); //ex: Python
         listCourses.add(new Curso());
         listCourses.add(new Curso());
         listCourses.add(new Curso());
         listCourses.add(new Curso());

         return listCourses;
    }

}
