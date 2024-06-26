package com.pluginKrylov;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.io.FileWriter;
import org.gradle.testkit.runner.GradleRunner;
import org.gradle.testkit.runner.BuildResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class GradlePluginKrylovGRPluginFunctionalTest {
    @TempDir
    File projectDir;

    private File getBuildFile() {
        return new File(projectDir, "build.gradle");
    }

    private File getSettingsFile() {
        return new File(projectDir, "settings.gradle");
    }

    @Test void canRunTask1() throws IOException {
        writeString(getSettingsFile(), "");
        writeString(getBuildFile(),
            "plugins {" +
            "  id('com.pluginKrylov.analysisAll')" +
            "}");

        // Run the build
        GradleRunner runner = GradleRunner.create();
        runner.forwardOutput();
        runner.withPluginClasspath();
        runner.withArguments("analysisAll");
        runner.withProjectDir(projectDir);
        BuildResult result = runner.build();

    }

    @Test void canRunTask2() throws IOException {
        writeString(getSettingsFile(), "");
        writeString(getBuildFile(),
                "plugins {" +
                        "  id('com.pluginKrylov.analysisJavaProjects')" +
                        "}");

        // Run the build
        GradleRunner runner = GradleRunner.create();
        runner.forwardOutput();
        runner.withPluginClasspath();
        runner.withArguments("analysisJavaProjects");
        runner.withProjectDir(projectDir);
        BuildResult result = runner.build();

    }

    @Test void canRunTask3() throws IOException {
        writeString(getSettingsFile(), "");
        writeString(getBuildFile(),
                "plugins {" +
                        "  id('com.pluginKrylov.analysisClassFiles')" +
                        "}");

        // Run the build
        GradleRunner runner = GradleRunner.create();
        runner.forwardOutput();
        runner.withPluginClasspath();
        runner.withArguments("analysisClassFiles");
        runner.withProjectDir(projectDir);
        BuildResult result = runner.build();

    }

    @Test void canRunTask4() throws IOException {
        writeString(getSettingsFile(), "");
        writeString(getBuildFile(),
                "plugins {" +
                        "  id('com.pluginKrylov.analysisFileResources')" +
                        "}");

        // Run the build
        GradleRunner runner = GradleRunner.create();
        runner.forwardOutput();
        runner.withPluginClasspath();
        runner.withArguments("analysisFileResources");
        runner.withProjectDir(projectDir);
        BuildResult result = runner.build();

    }

    private void writeString(File file, String string) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            writer.write(string);
        }
    }
}
