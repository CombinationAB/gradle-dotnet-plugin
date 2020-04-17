package dev.combination.dotnet

import org.gradle.api.Project

class DotNet {
    public static def exec(Project project, String[] arguments, String workDir = null, String verbosity = null, Map<String, Object> environmentVariables = null) {
        // Normally, dotnet should be in the path, but allow this to be overridden
        def dotnetExecutable = System.getProperty("dotnet.executable.path")
        if(dotnetExecutable == null) dotnetExecutable = "dotnet"

        def cmdline = [dotnetExecutable]
        if(arguments != null && arguments.length > 0)
            cmdline = [*cmdline, *arguments]

        if(verbosity != null)
            cmdline = [*cmdline, "--verbosity", verbosity]

        if(workDir == null)
            workDir = project.projectDir.absolutePath

		project.logger.info("Executing: " + cmdline.join(' '))
        return project.exec {
			workingDir workDir
            commandLine cmdline
            environment environmentVariables
        }
    }

    protected static Boolean isWindows() {
        return System.properties['os.name'].toLowerCase().contains('windows')
    }
}