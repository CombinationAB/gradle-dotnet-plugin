package org.combination.dotnet

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class DotNetPublishTask extends DotNetBuildTask {
    private Boolean selfContained
    private String manifest

    @TaskAction
    public def dotnetPublish() {
        def args = getArgs()
        if(selfContained)
            args += "--self-contained"
        if(manifest != null)
            args += ["--manifest", manifest]
        exec args
    }

    public void manifest(String manifest) {
        this.manifest = manifest
    }

    public void manifest(File manifest) {
        this.manifest = manifest.absolutePath
    }

    public void selfContained(Boolean selfContained) {
        this.selfContained = selfContained
    }
}