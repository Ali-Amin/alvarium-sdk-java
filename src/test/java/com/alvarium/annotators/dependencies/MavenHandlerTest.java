package com.alvarium.annotators.dependencies;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;


public class MavenHandlerTest {
    @Rule
    public TemporaryFolder dir = new TemporaryFolder();

    @Test
    public void shouldReturnAllPomDependencies() throws Exception {
        File project = dir.newFolder("project");
        File pom = new File(project.toPath() + "/pom.xml");

        StringBuilder pomContent = new StringBuilder();
        pomContent.append("<project>");
        pomContent.append("    <properties>");
        pomContent.append("        <maven.compiler.source>11</maven.compiler.source>");
        pomContent.append("    </properties>");
        pomContent.append("    <dependencies>");

        String groupId1 = "org.alvarium.sdk";
        String artifactId1 = "java-sdk";
        String version1 = "1.2.3";
        pomContent.append("        <dependency>");
        pomContent.append("        <groupId>" + groupId1 + "</groupId>");
        pomContent.append("        <artifactId>" + artifactId1 + "</artifactId>");
        pomContent.append("        <version>" + version1 + "</version>");
        pomContent.append("        </dependency>");

        String groupId2 = "org.foo.bar";
        String artifactId2 = "example";
        String version2 = "1.0.3a";
        pomContent.append("        <dependency>");
        pomContent.append("        <groupId>" + groupId2 + "</groupId>");
        pomContent.append("        <artifactId>" + artifactId2 + "</artifactId>");
        pomContent.append("        <version>" + version2 + "</version>");
        pomContent.append("        </dependency>");

        pomContent.append("    </dependencies>");
        pomContent.append("</project>");

        Files.writeString(pom.toPath(), pomContent.toString());
        MavenHandler handler = new MavenHandler(project.getAbsolutePath());

        assert handler.getFileName().equals(pom.getName());

        Map<String, String> packages = handler.getPackages();

        assert packages.get(groupId1 + ":" + artifactId1).equals(version1);
        assert packages.get(groupId2 + ":" + artifactId2).equals(version2);

        assert packages.keySet().size() == 2;

    }

}
