package com.pluginKrylov;

import org.gradle.api.Project;
import org.gradle.api.Plugin;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GradlePluginKrylovGRPlugin implements Plugin<Project> {
    public void apply(Project project) {

        boolean isDebug = project.getGradle().getStartParameter().getLogLevel().name().equals("DEBUG");

        AnalysisOfJavaProjects analysisOfJavaProjects = new AnalysisOfJavaProjects(isDebug);

        // Register a task
        project.getTasks().register("analysisAll",task -> {
           task.doLast(task1 -> {

               Scanner scanner = new Scanner(System.in);
               String currentPatch = scanner.nextLine();

               // Output the full path to the root cat
               if(isDebug) {
                   System.out.println("\nI'M OPENING THE ROOT CAT: " + currentPatch);
                   System.out.print("\n");
               }

               // We get a file with this name
               File dir = new File(currentPatch);
               // Creating an array of files and directories and folders
               File[] list = dir.listFiles();

               if(isDebug) {
                   System.out.println("I'M STARTING TO SEARCH FOR JAVA FILES: " + "\n");
               }

               try {
                   analysisOfJavaProjects.checkCountJavaFiles(list);
               } catch (FileNotFoundException e) {
                   throw new RuntimeException(e);
               }

               if(isDebug) {
                   System.out.println("I'M STARTING TO LOOK FOR CLASS FILES: " + "\n");
               }

               analysisOfJavaProjects.checkFilesClass(list);

               if(isDebug) {
                   System.out.println("I'M STARTING TO SEARCH FOR RESOURCE FILES: " + "\n");
               }

               analysisOfJavaProjects.checkResourcesFile(list);

               if(isDebug) {
                   System.out.println("\nSearch results:");
               }

               System.out.println("\nThe number of Java files found: " + analysisOfJavaProjects.getCountProjectFilJava());
               System.out.println("The number of class files found: " + analysisOfJavaProjects.getCountProjectClass());
               System.out.println("The number of resource files found: " + analysisOfJavaProjects.getCountProjectFileResources());

               analysisOfJavaProjects.deleteCountProjectFileJava();
               analysisOfJavaProjects.deleteCountProjectClass();
               analysisOfJavaProjects.deleteCountProjectFileResources();

               System.out.println("\n");

           });
        });

        project.getTasks().register("analysisJavaProjects",task -> {
           task.doLast(task1 -> {

               Scanner scanner = new Scanner(System.in);
               String currentPatch = scanner.nextLine();

               // Output the full path to the root cat
               if(isDebug) {
                   System.out.println("\nI'M OPENING THE ROOT CAT: " + currentPatch);
                   System.out.print("\n");
               }

               // We get a file with this name
               File dir = new File(currentPatch);
               // Creating an array of files and directories and folders
               File[] list = dir.listFiles();

               if(isDebug) {
                   System.out.println("I'M STARTING TO SEARCH FOR JAVA FILES: " + "\n");
               }

               try {
                   analysisOfJavaProjects.checkCountJavaFiles(list);
               } catch (FileNotFoundException e) {
                   throw new RuntimeException(e);
               }

               if(isDebug) {
                   System.out.println("\nSearch results:" + "\n");
               }

               System.out.println("\nThe number of Java files found: " + analysisOfJavaProjects.getCountProjectFilJava());

               analysisOfJavaProjects.deleteCountProjectFileJava();

               System.out.println("\n");

           });
        });

        project.getTasks().register("analysisClassFiles",task -> {
           task.doLast(task1 -> {

               Scanner scanner = new Scanner(System.in);
               String currentPatch = scanner.nextLine();

               // Output the full path to the root cat
               if(isDebug) {
                   System.out.println("\nI'M OPENING THE ROOT CAT: " + currentPatch);
                   System.out.print("\n");
               }

               // We get a file with this name
               File dir = new File(currentPatch);
               // Creating an array of files and directories and folders
               File[] list = dir.listFiles();

               if(isDebug) {
                   System.out.println("I'M STARTING TO LOOK FOR CLASS FILES: " + "\n");
               }

               analysisOfJavaProjects.checkFilesClass(list);

               if(isDebug) {
                   System.out.println("\nSearch results:");
               }

               System.out.println("\nThe number of class files found: " + analysisOfJavaProjects.getCountProjectClass());

               analysisOfJavaProjects.deleteCountProjectClass();

               System.out.println("\n");

           });
        });

        project.getTasks().register("analysisFileResources",task -> {
           task.doLast(task1 -> {

               Scanner scanner = new Scanner(System.in);
               String currentPatch = scanner.nextLine();

               // Output the full path to the root cat
               if(isDebug) {
                   System.out.println("\nI'M OPENING THE ROOT CAT: " + currentPatch);
                   System.out.print("\n");
               }

               // We get a file with this name
               File dir = new File(currentPatch);
               // Creating an array of files and directories and folders
               File[] list = dir.listFiles();

               if(isDebug) {
                   System.out.println("I'M STARTING TO SEARCH FOR RESOURCE FILES: " + "\n");
               }

               analysisOfJavaProjects.checkResourcesFile(list);

               if(isDebug) {
                   System.out.println("\nSearch results:");
               }

               System.out.println("\nThe number of resource files found: " + analysisOfJavaProjects.getCountProjectFileResources());

               analysisOfJavaProjects.deleteCountProjectFileResources();

               System.out.println("\n");

           });
        });

    }
}
