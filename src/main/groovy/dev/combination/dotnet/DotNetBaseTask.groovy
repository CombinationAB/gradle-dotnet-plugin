package dev.combination.dotnet

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class DotNetBaseTask extends DefaultTask {
    protected String workingDir, verbosity
    protected def extraArgs = []

    protected def exec(String... arguments) {
        return DotNet.exec(project, arguments, workingDir, verbosity)
    }

    public void workingDir(String workingDir) {
        this.workingDir = workingDir
    }

    public void workingDir(File workingDir) {
        this.workingDir = workingDir.absolutePath
    }

    public void verbosity(String verbosity) {
        this.verbosity = verbosity.toLowerCase()
    }

    public void extraArg(String arg) {
        this.extraArgs += [arg]
    }

    public abstract def run()
}