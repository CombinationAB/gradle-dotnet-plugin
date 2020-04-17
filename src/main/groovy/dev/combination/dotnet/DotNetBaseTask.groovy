package dev.combination.dotnet

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class DotNetBaseTask extends DefaultTask {
    protected String workingDir, verbosity
    protected def extraArgs = []
    protected HashMap<String, Object> environmentVariables = new HashMap<String, Object>()

    protected def exec(String... arguments) {
        return DotNet.exec(project, arguments, workingDir, verbosity, environmentVariables)
    }

    public void extraArg(String arg) {
        this.extraArgs += [arg]
    }

    public void workingDir(String workingDir) {
        this.workingDir = workingDir
    }

    public void verbosity(String verbosity) {
        this.verbosity = verbosity.toLowerCase()
    }

    public void workingDir(File workingDir) {
        this.workingDir = workingDir.absolutePath
    }
    
    public void environmentVariable(String key, Object value) {
        this.environmentVariables[key] = value
    }
    
    public void environmentVariables(HashMap<String, Object> values) {
        this.environmentVariables << values
    }

    public abstract def run()
}