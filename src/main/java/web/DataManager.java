package web;

import data.SoapRequest;
import entities.Project;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    public static List<Project> getProjects(int number){
        return SoapRequest.getProjects(number);
    }
}
