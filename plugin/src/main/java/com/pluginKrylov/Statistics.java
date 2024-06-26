package com.pluginKrylov;

public class Statistics {


    /*

    The fields are marked here....

     */

    private static int countProjectFileJava = 0;

    private static int countProjectFileResources = 0;

    private static int countProjectClass = 0;

    private static int countStrJavaCode = 0;

    private static int countStrCommentJavaCode = 0;

    private static int countMultilineComments = 0;

    private static int countMethodPublic = 0;

    private static int countMethodPrivate = 0;

    private static int countMethodProtected = 0;

    private static int countImports = 0;

    private static int countNumberPackages = 0;

    /*

    This is where the methods of increasing the counter by 1 begin....

     */

    public void plusCountProjectFileJava(){
        countProjectFileJava++;
    }

    public void plusCountProjectFileResources(){
        countProjectFileResources++;
    }

    public void plusCountProjectClass(){
        countProjectClass++;
    }

    public void plusCountStrJavaCode(){
        countStrJavaCode++;
    }

    public void plusCountStrCommentJavaCode(){
        countStrCommentJavaCode++;
    }

    public void plusCountMultilineComments(){
        countMultilineComments++;
    }

    public void plusCountMethodPublic(){
        countMethodPublic++;
    }

    public void plusCountMethodPrivate(){
        countMethodPrivate++;
    }

    public void plusCountMethodProtected(){
        countMethodProtected++;
    }

    public void plusCountImports(){
        countImports++;
    }

    public void plusCountNumberPackages(){
        countNumberPackages++;
    }

    /*

    This is where the output methods (geters) begin.......

     */

    public int getCountProjectFileJava(){
        return countProjectFileJava;
    }

    public int getCountProjectFileResources(){
        return countProjectFileResources;
    }

    public int getCountProjectClass(){
        return countProjectClass;
    }

    public int getCountStrJavaCode(){
        return countStrJavaCode;
    }

    public int getCountStrCommentJavaCode(){
        return countStrCommentJavaCode;
    }

    public int getCountMultilineComments(){
        return countMultilineComments;
    }

    public int getCountMethodPublic(){
        return countMethodPublic;
    }

    public int getCountMethodPrivate(){
        return countMethodPrivate;
    }

    public int getCountMethodProtected(){
        return countMethodProtected;
    }

    public int getCountImports(){
        return countImports;
    }

    public int getCountNumberPackages(){
        return countNumberPackages;
    }

    /*

    This is where the cleaning methods begin.......

     */

    public void clearCountProjectFileJava(){
        countProjectFileJava = 0;
    }

    public void clearCountProjectFileResources(){
        countProjectFileResources = 0;
    }

    public void clearCountProjectClass(){
        countProjectClass = 0;
    }

    public void clearCountStrJavaCode(){
        countStrJavaCode = 0;
    }

    public void clearCountStrCommentJavaCode(){
        countStrCommentJavaCode = 0;
    }

    public void clearCountMultilineComments(){
        countMultilineComments = 0;
    }

    public void clearCountMethodPublic(){
        countMethodPublic = 0;
    }

    public void clearCountMethodPrivate(){
        countMethodPrivate = 0;
    }

    public void clearCountMethodProtected(){
        countMethodProtected = 0;
    }

    public void clearCountImports(){
        countImports = 0;
    }

    public void clearCountNumberPackages(){
        countNumberPackages = 0;
    }

}
