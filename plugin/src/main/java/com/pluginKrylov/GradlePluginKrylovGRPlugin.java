package com.pluginKrylov;

import org.gradle.api.Project;
import org.gradle.api.Plugin;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GradlePluginKrylovGRPlugin implements Plugin<Project> {
    public void apply(Project project) {

        // Register a task
        project.getTasks().register("analysis",task -> {
           task.doLast(task1 -> {

               Scanner scanner = new Scanner(System.in);
               String currentPatch = scanner.nextLine();

               // Output the full path to the root cat
               System.out.println("\nI'm opening the root catlog: " + currentPatch + "\n");
               System.out.print("\n");

               // We get a file with this name
               File dir = new File(currentPatch);
               // Creating an array of files and directories and folders
               File[] List = dir.listFiles();

               analysisOfJavaProjects analysisOfJavaProjects = new analysisOfJavaProjects();

               try {
                   analysisOfJavaProjects.checkCountJavaFiles(List);
               } catch (FileNotFoundException e) {
                   throw new RuntimeException(e);
               }
               analysisOfJavaProjects.checkFilesClass(List);
               analysisOfJavaProjects.checkResourcesFile(List);

               System.out.println("\nSearch results:" + "\n");
               System.out.println("The number of Java files found: " + analysisOfJavaProjects.getCountProjectFilJava());
               System.out.println("The number of class files found: " + analysisOfJavaProjects.getCountProjectClass());
               System.out.println("The number of resource files found: " + analysisOfJavaProjects.getCountProjectFileResources());

               analysisOfJavaProjects.deleteCountProjectFileJava();
               analysisOfJavaProjects.deleteCountProjectClass();
               analysisOfJavaProjects.deleteCountProjectFileResources();

           });
        });

    }
}
