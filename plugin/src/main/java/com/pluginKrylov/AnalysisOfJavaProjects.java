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
    private final String separator = File.separator;

    // Pattern
    private static Pattern pattern;

    // RegV
    private static String reg;

    // Matcher
    private static Matcher matcher;

    private boolean isDebug;

    public AnalysisOfJavaProjects(boolean isDebug){
        this.isDebug = isDebug;
    }

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

    public void checkMultiLineComments(StringBuilder textSB){

        // Checking for multi-line comments
        reg = "\\/\\*[A-zА-я0-9\\W][^\\/\\*]+";
        pattern = Pattern.compile(reg);

        matcher = pattern.matcher(textSB.toString());

        // If I see a multi-line comment, I increase the counter
        while(matcher.find()){
            statistics.plusCountMultilineComments();
        }

    }

    public void checkMethod(StringBuilder textSB){

        // Checking for methods
        reg = "(public|private|protected)[A-z0-9 ,]+\\(([A-z0-9 ,]+|)\\)";
        pattern = Pattern.compile(reg);

        matcher = pattern.matcher(textSB.toString());

        // If there are methods, I look at the access modifier
        while(matcher.find()){

            String MethodStr = matcher.group();
            reg = "(public|private|protected)";

            Pattern patternMD = Pattern.compile(reg);
            Matcher matcherMD = patternMD.matcher(MethodStr);

            while(matcherMD.find()){

                switch(matcherMD.group()){
                    case("public"):{
                        statistics.plusCountMethodPublic();
                        break;
                    }
                    case("private"):{
                        statistics.plusCountMethodPrivate();
                        break;
                    }
                    case("protected"):{
                        statistics.plusCountMethodProtected();
                        break;
                    }
                }
            }
        }

    }

    public void checkImport(StringBuilder textSB){

        // Checking for imports
        reg = "import [ .А-яA-z0-9*]+;";
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(textSB.toString());

        while(matcher.find()){
            statistics.plusCountImports();
        }

    }

    public void checkPackage(StringBuilder textSB){

        // Checking for package
        reg = "package [A-zА-я0-9.$*]+;";
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(textSB.toString());

        while(matcher.find()){
            statistics.plusCountNumberPackages();
        }

    }

    public void checkComment(File file) throws FileNotFoundException {

        Scanner scannerCheckComment = new Scanner(file);

        // Counting the number of lines of code and comments
        while(scannerCheckComment.hasNextLine()){

            String javaStr = scannerCheckComment.nextLine();
            if(javaStr.contains("//")){
                statistics.plusCountStrCommentJavaCode();
            }

            statistics.plusCountStrJavaCode();

        }

    }

    public void informationOutput(File file){

        // Data output
        System.out.println("The amount of Java code in the file: " + file + " = " + statistics.getCountStrJavaCode());
        System.out.println("The number of comments in the Java code: " + file + " = " + statistics.getCountStrCommentJavaCode());
        System.out.println("The number of multiline comments: " + file + " = " + statistics.getCountMultilineComments() + "\n");

        if(isDebug) {
            System.out.println("Number of public methods: " + file + " = " + statistics.getCountMethodPublic());
            System.out.println("Number of private methods: " + file + " = " + statistics.getCountMethodPrivate());
            System.out.println("Number of protected methods: " + file + " = " + statistics.getCountMethodProtected() + "\n");
            System.out.println("Number of imported modules: " + file + " = " + statistics.getCountImports() + "\n");
            System.out.println("Number of packages: " + file + " = " + statistics.getCountNumberPackages());
        }

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

    public void checkCountJavaFiles(File[] files) throws FileNotFoundException {

        for(File file : files){

            if(file.isDirectory()){

                // I take a directory (folder) and divide it into a list of files.
                File directory = new File(String.valueOf(file));
                File[] contentDirectory = directory.listFiles();

                // Checking that the directory is not empty
                assert contentDirectory != null;
                // I use recursion
                checkCountJavaFiles(contentDirectory);

            }
            else{

                // I divide the file line by a point to find the extension
                String nameFile = file.getName();

                // If the extension .java is fulfilling the condition
                if(nameFile.lastIndexOf("java") > 0){

                    // Message to the user
                    System.out.println("\nJAVA FILE FOUND: " + file + "\n");

                    // I'm increasing the counter by 1
                    statistics.plusCountProjectFileJava();

                    try {

                        // I'm collecting the text
                        StringBuilder textSB = new StringBuilder();
                        Scanner scannerTextConcat = new Scanner(file);

                        // I'm collecting the text
                        while(scannerTextConcat.hasNextLine()){
                            textSB.append(scannerTextConcat.nextLine()).append("\n");
                        }

                        checkMultiLineComments(textSB);

                        checkMethod(textSB);

                        checkImport(textSB);

                        checkPackage(textSB);

                        checkComment(file);

                    } catch (FileNotFoundException e) {
                        System.out.println("There is no such file, or it cannot be found!\n");
                        throw new FileNotFoundException();
                    }

                    // Data output
                    System.out.println("The amount of Java code in the file: " + file + " = " + statistics.getCountStrJavaCode());
                    System.out.println("The number of comments in the Java code: " + file + " = " + statistics.getCountStrCommentJavaCode());
                    System.out.println("The number of multiline comments: " + file + " = " + statistics.getCountMultilineComments() + "\n");

                    if(isDebug) {
                        System.out.println("Number of public methods: " + file + " = " + statistics.getCountMethodPublic());
                        System.out.println("Number of private methods: " + file + " = " + statistics.getCountMethodPrivate());
                        System.out.println("Number of protected methods: " + file + " = " + statistics.getCountMethodProtected() + "\n");
                        System.out.println("Number of imported modules: " + file + " = " + statistics.getCountImports() + "\n");
                        System.out.println("Number of packages: " + file + " = " + statistics.getCountNumberPackages());
                    }

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

                File directory = new File(String.valueOf(file));
                File[] contentDirectory = directory.listFiles();

                assert contentDirectory != null;
                checkFilesClass(contentDirectory);

            } else {

                String nameFile = file.getName();

                // Checking for classes
                if ((nameFile.lastIndexOf("class") > 0)){

                    if(isDebug) {
                        System.out.println("THE CLASS FILE WAS FOUND: " + file + "\n");
                    }

                    statistics.plusCountProjectClass();

                }
            }
        }
    }

    public void checkResourcesFile(File[] files){

        for(File fileOne : files){

            File directory = new File(String.valueOf(fileOne));
            File[] fileResources = directory.listFiles();

            if(fileOne.isDirectory()){

                if(String.valueOf(fileOne).contains("resources")){

                    // Checking for resources
                    assert fileResources != null;
                    for (File fileTwo : fileResources){

                        if(isDebug) {
                            System.out.println("\nRESOURCE FILE FOUND: " + fileTwo + "\n");
                        }

                        statistics.plusCountProjectFileResources();
                    }

                }

                assert fileResources != null;
                checkResourcesFile(fileResources);

            }

        }

    }
}
