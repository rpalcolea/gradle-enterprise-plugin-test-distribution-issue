package com.netflix;

import com.gradle.enterprise.gradleplugin.GradleEnterpriseExtension;
import com.gradle.scan.plugin.BuildScanExtension;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class MyPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        GradleEnterpriseExtension gradleEnterpriseExtension = project.getRootProject().getExtensions().findByType(GradleEnterpriseExtension.class);
        if (gradleEnterpriseExtension != null) {
            BuildScanExtension buildScan = gradleEnterpriseExtension.getBuildScan();
            buildScan.tag("MyTag");
        }
    }
}
