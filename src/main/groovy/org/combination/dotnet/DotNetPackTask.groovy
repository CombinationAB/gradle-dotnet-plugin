package org.combination.dotnet

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class DotNetPackTask extends DotNetBuildTask {
    private Boolean includeSource, includeSymbols, servicable, noBuild

    @TaskAction
    public def dotnetPack() {
        def args = getArgs()
        if(includeSource)
            args += "--include-source"
        if(includeSymbols)
            args += "--include-symbols"
        if(servicable)
            args += "--servicable"
        if(noBuild)
            args += "--no-build"
        exec args
    }

    public void manifest(String manifest) {
        this.manifest = manifest
    }

    public void manifest(File manifest) {
        this.manifest = manifest.absolutePath
    }

    public void includeSource(Boolean includeSource) {
        this.includeSource = includeSource
    }

    public void includeSymbols(Boolean includeSymbols) {
        this.includeSymbols = includeSymbols
    }

    public void servicable(Boolean servicable) {
        this.servicable = servicable
    }

    public void noBuild(Boolean noBuild) {
        this.noBuild = noBuild
    }
}