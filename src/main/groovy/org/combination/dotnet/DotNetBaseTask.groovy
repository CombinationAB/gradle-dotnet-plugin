package org.combination.dotnet

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class DotNetBaseTask extends DefaultTask {
    protected String workingDir, verbosity

    protected def exec(String... arguments) {
        // Normally, dotnet should be in the path, but allow this to be overridden
        def dotnetExecutable = System.getProperty("dotnet.executable.path")
        if(dotnetExecutable == null) dotnetExecutable = "dotnet"

        def commandLine = [dotnetExecutable]
        if(arguments != null && arguments.length > 0)
            commandLine = [*commandLine, *arguments]

        if(verbosity != null)
            commandLine = [*commandLine, "--verbosity", verbosity]

        def wd = workingDir
        if(wd == null)
            wd = project.projectDir.absolutePath

        return project.exec {
			workingDir wd
            commandLine commandLine
        }
    }

    protected static Boolean isWindows() {
        return System.properties['os.name'].toLowerCase().contains('windows')
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
}