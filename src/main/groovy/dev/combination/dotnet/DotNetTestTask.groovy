package dev.combination.dotnet

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class DotNetTestTask extends DotNetBuildTask {
    private boolean noBuild, listTests
    private def filters
    private String testAdapter, settings, resultsDirectory, logger, collect, diag

    @TaskAction
    public def run() {
        def args = getArgs()
        args[0] = "test"
        if(testAdapter != null)
            args += ["--testAdapter", testAdapter]
        if(settings != null)
            args += ["--settings", settings]
        if(logger != null)
            args += ["--logger", logger]
        if(diag != null)
            args += ["--diag", diag]
        if(collect != null)
            args += ["--collect", collect]
        if(diag != null)
            args += ["--diag", diag]
        if(resultsDirectory != null)
            args += ["--results-directory", resultsDirectory]
        if(listTests)
            args += "--list-tests"
        if(noBuild)
            args += "--no-build"            
        for(String filter : filters) {
            args += ["--filter", filter]
        }

        args += extraArgs
        exec (args as String[])
    }

    public void settings(String settings) {
        this.settings = settings
    }

    public void settings(File settings) {
        this.settings = settings.absolutePath
    }

    public void testAdapter(String testAdapter) {
        this.testAdapter = testAdapter
    }

    public void testAdapter(File testAdapter) {
        this.testAdapter = testAdapter.absolutePath
    }

    public void logger(String logger) {
        this.logger = logger
    }

    public void filter(String filter) {
        this.filters += [filter]
    }
    
    public void collect(String collect) {
        this.collect = collect
    }

    public void diag(String diag) {
        this.diag = diag
    }

    public void diag(File diag) {
        this.diag = diag.absolutePath
    }

    public void resultsDirectory(String resultsDirectory) {
        this.resultsDirectory = resultsDirectory
    }

    public void manifest(File resultsDirectory) {
        this.resultsDirectory = resultsDirectory.absolutePath
    }

    public void noBuild(boolean noBuild) {
        this.noBuild = noBuild
    }

    public void listTests(boolean listTests) {
        this.listTests = listTests
    }
}