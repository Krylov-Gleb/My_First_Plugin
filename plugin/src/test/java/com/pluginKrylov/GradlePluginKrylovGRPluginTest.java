package com.pluginKrylov;

import org.gradle.testfixtures.ProjectBuilder;
import org.gradle.api.Project;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GradlePluginKrylovGRPluginTest {
    @Test void pluginRegistersATask() {
        // Create a test project and apply the plugin
        Project project = ProjectBuilder.builder().build();
        project.getPlugins().apply("com.pluginKrylov.analysis");

        // Verify the result
        assertNotNull(project.getTasks().findByName("analysis"));
    }
}
