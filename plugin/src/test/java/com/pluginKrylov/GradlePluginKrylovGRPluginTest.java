package com.pluginKrylov;

import org.gradle.testfixtures.ProjectBuilder;
import org.gradle.api.Project;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GradlePluginKrylovGRPluginTest {
    @Test void pluginRegistersATask1() {
        // Create a test project and apply the plugin
        Project project = ProjectBuilder.builder().build();
        project.getPlugins().apply("com.pluginKrylov.analysisAll");

        // Verify the result
        assertNotNull(project.getTasks().findByName("analysisAll"));
    }
    @Test void pluginRegistersATask2() {
        // Create a test project and apply the plugin
        Project project = ProjectBuilder.builder().build();
        project.getPlugins().apply("com.pluginKrylov.analysisJavaProjects");

        // Verify the result
        assertNotNull(project.getTasks().findByName("analysisJavaProjects"));
    }
    @Test void pluginRegistersATask3() {
        // Create a test project and apply the plugin
        Project project = ProjectBuilder.builder().build();
        project.getPlugins().apply("com.pluginKrylov.analysisClassFiles");

        // Verify the result
        assertNotNull(project.getTasks().findByName("analysisClassFiles"));
    }
    @Test void pluginRegistersATask4() {
        // Create a test project and apply the plugin
        Project project = ProjectBuilder.builder().build();
        project.getPlugins().apply("com.pluginKrylov.analysisFileResources");

        // Verify the result
        assertNotNull(project.getTasks().findByName("analysisFileResources"));
    }
}
