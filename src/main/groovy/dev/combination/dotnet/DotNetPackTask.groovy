package dev.combination.dotnet

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class DotNetPackTask extends DotNetBuildTask {
    private boolean includeSource, includeSymbols, servicable, noBuild

    @TaskAction
    public def run() {
        String[] args = getArgs()
        args[0] = "pack"
        if(includeSource)
            args += "--include-source"
        if(includeSymbols)
            args += "--include-symbols"
        if(servicable)
            args += "--servicable"
        if(noBuild)
            args += "--no-build"
        args += extraArgs
		project.logger.info((["dotnet"] + args).join(' '))
        exec args
    }

    public void manifest(String manifest) {
        this.manifest = manifest
    }

    public void manifest(File manifest) {
        this.manifest = manifest.absolutePath
    }

    public void includeSource(boolean includeSource) {
        this.includeSource = includeSource
    }

    public void includeSymbols(boolean includeSymbols) {
        this.includeSymbols = includeSymbols
    }

    public void servicable(boolean servicable) {
        this.servicable = servicable
    }

    public void noBuild(boolean noBuild) {
        this.noBuild = noBuild
    }
}