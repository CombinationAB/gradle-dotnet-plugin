package dev.combination.dotnet

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class DotNetRestoreTask extends DotNetBaseTask {
    private boolean noCache, noDependencies, force
    private String projectName, packages, configfile
    private def sources = []
    private def runtimes = []

    @TaskAction
    public def run() {
        String[] args = ["restore"]
        if(projectName != null)
            args += projectName
        if(configfile != null)
            args += ["--configfile", configfile]
        if(packages != null)
            args += ["--packages", packages]
        if(noCache)
            args += ["--no-cache"]
        if(noDependencies)
            args += ["--no-dependencies"]
        if(force)
            args += ["--force"]
        for(String source : sources) {
            args += ["--source", source]
        }
        for(String runtime : runtimes) {
            args += ["--runtime", runtime]
        }
        args += extraArgs
		project.logger.info((["dotnet"] + args).join(' '))
        exec args
    }

    public void project(String project) {
        this.projectName = project
    }

    public void project(File project) {
        this.projectName = project.absolutePath
    }

    public void configfile(String configfile) {
        this.configfile = configfile
    }

    public void configfile(File configfile) {
        this.configfile = configfile.absolutePath
    }

    public void noCache(boolean noCache) {
        this.noCache = noCache
    }

    public void force(boolean force) {
        this.force = force
    }

    public void noDependencies(boolean noDependencies) {
        this.noDependencies = noDependencies
    }

    public void packages(String packages) {
        this.packages = packages
    }

    public void packages(File packages) {
        this.packages = packages.absolutePath
    }

    public void source(String source) {
        this.sources = [*this.sources, source]
    }

    public void source(File source) {
        this.source(source.absolutePath)
    }

    public void runtime(String runtime) {
        this.runtimes = [*this.runtimes, runtime]
    }
}