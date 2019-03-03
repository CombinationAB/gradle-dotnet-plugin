package dev.combination.dotnet

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class DotNetPublishTask extends DotNetBuildTask {
    private boolean selfContained
    private String manifest

    @TaskAction
    public def run() {
        def args = getArgs()
        args[0] = "publish"
        if(selfContained)
            args += "--self-contained"
        if(manifest != null)
            args += ["--manifest", manifest]
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

    public void selfContained(boolean selfContained) {
        this.selfContained = selfContained
    }
}