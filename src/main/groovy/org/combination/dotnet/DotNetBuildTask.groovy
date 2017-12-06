package org.combination.dotnet

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class DotNetBuildTask extends DotNetBaseTask {
    private Boolean noDependencies, noIncremental, noRestore, force
    private String project, configuration, framework, output, runtime, versionSuffix
    private def sources = []
    private def runtimes = []

    protected String[] getArgs() {
        args = []
        if(project != null)
            args += project
        if(configuration != null)
            args += ["--configuration", configuration]
        if(framework != null)
            args += ["--framework", framework]
        if(runtime != null)
            args += ["--runtime", runtime]
        if(output != null)
            args += ["--output", output]
        if(versionSuffix != null)
            args += ["--version-suffix", versionSuffix]
        if(noDependencies)
            args += ["--no-dependencies"]
        if(force)
            args += ["--force"]
        if(noIncremental)
            args += ["--no-incremental"]
        if(noRestore)
            args += ["--no-restore"]
        return args
    }

    @TaskAction
    public def dotnetBuild() {
        exec getArgs()
    }

    public void project(String project) {
        this.project = project
    }

    public void project(File project) {
        this.project = project.absolutePath
    }

    public void output(String output) {
        this.output = output
    }

    public void output(File output) {
        this.output = output.absolutePath
    }

    public void configuration(String configuration) {
        this.configuration = configuration
    }

    public void framework(String framework) {
        this.framework = framework
    }

    public void force(Boolean force) {
        this.force = force
    }

    public void noDependencies(Boolean noDependencies) {
        this.noDependencies = noDependencies
    }

    public void noIncremental(Boolean noIncremental) {
        this.noIncremental = noIncremental
    }

    public void noRestore(Boolean noRestore) {
        this.noRestore = noRestore
    }

    public void versionSuffix(String versionSuffix) {
        this.versionSuffix = versionSuffix
    }
}