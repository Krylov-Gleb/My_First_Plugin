package com.pluginKrylov;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalysisOfJavaProjects {

    // Create class statistics
    private final Statistics statistics = new Statistics();

    // Add separator (/) or (\)
    String separator = File.separator;

    // Getter - return count java files
    public int getCountProjectFilJava(){
        return statistics.getCountProjectFileJava();
    }

    public int getCountProjectFileResources() {
        return statistics.getCountProjectFileResources();
    }

    public int getCountProjectClass(){
        return statistics.getCountProjectClass();
    }

    // Delete Count_Project_File_Java
    public void deleteCountProjectFileJava(){
        statistics.clearCountProjectFileJava();
    }

    public void deleteCountProjectFileResources(){
        statistics.clearCountProjectFileResources();
    }

    public void deleteCountProjectClass(){
        statistics.clearCountProjectClass();
    }

    public void checkCountJavaFiles(File[] files) throws FileNotFoundException {

        for(File file : files){

            if(file.isDirectory()){

                // I take a directory (folder) and divide it into a list of files.
                File Directory = new File(String.valueOf(file));
                File[] Content_Directory = Directory.listFiles();

                // Checking that the directory is not empty
                assert Content_Directory != null;
                // I use recursion
                checkCountJavaFiles(Content_Directory);

            }
            else{

                // I divide the file line by a point to find the extension
                String[] Split = String.valueOf(file).split("\\.");

                // If the extension .java is fulfilling the condition
                if(Split[Split.length-1].equals("java")){
                    // Message to the user
                    System.out.println("JAVA FILE FOUND: " + file + "\n");
                    // I'm increasing the counter by 1
                    statistics.plusCountProjectFileJava();

                    try {

                        // I'm collecting the text
                        String Text = "";
                        Scanner scanner1 = new Scanner(file);

                        // I'm collecting the text
                        while(scanner1.hasNextLine()){
                            Text = Text + scanner1.nextLine() + "\n";
                        }

                        // Checking for multi-line comments
                        String RegDefault = "\\/\\*[A-zА-я0-9\\W][^\\/\\*]+";
                        Pattern patternMultilineCommentsDefault = Pattern.compile(RegDefault);

                        Matcher matcherMultilineCommentsDefault = patternMultilineCommentsDefault.matcher(Text);

                        // If I see a multi-line comment, I increase the counter
                        while(matcherMultilineCommentsDefault.find()){
                            statistics.plusCountMultilineComments();
                        }

//                        String RegStars = "\\/\\*\\*[A-zА-я0-9\\W][^\\/\\*]+";
//                        Pattern patternMultilineCommentsStars = Pattern.compile(RegStars);
//
//                        Matcher matcherMultilineCommentsStars = patternMultilineCommentsStars.matcher(Text);
//
//                        while(matcherMultilineCommentsStars.find()){
//                            statistics.plusCountMultilineComments();
//                        }

                        // Checking for methods
                        String RegMethod = "(public|private|protected)[A-z0-9 ,]+\\(([A-z0-9 ,]+|)\\)";
                        Pattern patternMethod = Pattern.compile(RegMethod);

                        Matcher matcherMethod = patternMethod.matcher(Text);

                        // If there are methods, I look at the access modifier
                        while(matcherMethod.find()){

                            String MethodStr = matcherMethod.group();
                            String RegMD = "(public|private|protected)";

                            Pattern patternMD = Pattern.compile(RegMD);
                            Matcher matcherMD = patternMD.matcher(MethodStr);

                            while(matcherMD.find()){

                                if(matcherMD.group().equals("public")){
                                    statistics.plusCountMethodPublic();
                                    break;
                                }
                                if(matcherMD.group().equals("private")){
                                    statistics.plusCountMethodPrivate();
                                    break;
                                }
                                if (matcherMD.group().equals("protected")){
                                    statistics.plusCountMethodProtected();
                                    break;
                                }
                            }
                        }

                        // Checking for imports
                        String RegImport = "import [ .А-яA-z0-9*]+;";
                        Pattern patternImports = Pattern.compile(RegImport);
                        Matcher matcherImports = patternImports.matcher(Text);

                        while(matcherImports.find()){
                            statistics.plusCountImports();
                        }

                        // Checking for package
                        String RegPackage = "package [A-zА-я0-9.$*]+;";
                        Pattern patternPackage = Pattern.compile(RegPackage);
                        Matcher matcherPackage = patternPackage.matcher(Text);

                        while(matcherPackage.find()){
                            statistics.plusCountNumberPackages();
                        }

                        Scanner scanner2 = new Scanner(file);

                        // Counting the number of lines of code and comments
                        while(scanner2.hasNextLine()){

                            String Java_Str = scanner2.nextLine();
                            if(Java_Str.contains("//")){
                                statistics.plusCountStrCommentJavaCode();
                            }

                            statistics.plusCountStrJavaCode();

                        }

                    } catch (FileNotFoundException e) {
                        System.out.println("There is no such file, or it cannot be found!\n");
                        throw new FileNotFoundException();
                    }

                    // Data output
                    System.out.println("The amount of Java code in the file: " + file + " = " + statistics.getCountStrJavaCode());
                    System.out.println("The number of comments in the Java code: " + file + " = " + statistics.getCountStrCommentJavaCode());
                    System.out.println("The number of multiline comments: " + file + " = " + statistics.getCountMultilineComments() + "\n");
                    System.out.println("Number of public methods: " + file + " = " + statistics.getCountMethodPublic());
                    System.out.println("Number of private methods: " + file + " = " + statistics.getCountMethodPrivate());
                    System.out.println("Number of protected methods: " + file + " = " + statistics.getCountMethodProtected() + "\n");
                    System.out.println("Number of imported modules: " + file + " = " + statistics.getCountImports() +  "\n");
                    System.out.println("Number of packages: " + file + " = " + statistics.getCountNumberPackages());

                    System.out.println("\n");

                    // Cleaning the counter
                    statistics.clearCountStrCommentJavaCode();
                    statistics.clearCountStrJavaCode();
                    statistics.clearCountMultilineComments();
                    statistics.clearCountMethodPublic();
                    statistics.clearCountMethodPrivate();
                    statistics.clearCountMethodProtected();
                    statistics.clearCountImports();
                    statistics.clearCountNumberPackages();

                }

            }

        }

    }

    public void checkFilesClass(File[] files) {

        for (File file : files) {

            if (file.isDirectory()) {

                File Directory = new File(String.valueOf(file));
                File[] Content_Directory = Directory.listFiles();

                assert Content_Directory != null;
                checkFilesClass(Content_Directory);

            } else {

                String[] Split = String.valueOf(file).split("\\.");

                // Checking for classes
                if (Split[Split.length - 1].equals("class")) {
                    System.out.println("THE CLASS FILE WAS FOUND: " + file + "\n");
                    statistics.plusCountProjectClass();

                }
            }
        }
    }

    public void checkResourcesFile(File[] files){

        for(File file : files){

            File Directory = new File(String.valueOf(file));
            File[] file_resources = Directory.listFiles();

            if(file.isDirectory()){

                if(String.valueOf(file).contains("resources")){

                    // Checking for resources
                    assert file_resources != null;
                    for (File file1 : file_resources){
                        System.out.println("\nRESOURCE FILE FOUND: " + file1 + "\n");
                        statistics.plusCountProjectFileResources();
                    }

                }

                assert file_resources != null;
                checkResourcesFile(file_resources);

            }

        }

    }
}
