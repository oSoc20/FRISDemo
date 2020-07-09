package web;

import data.SoapRepository;
import entities.Project;

import java.util.List;

public class DataManager {

    public static List<Project> getProjects(int number){
        return SoapRepository.getProjects(number);
    }
}
