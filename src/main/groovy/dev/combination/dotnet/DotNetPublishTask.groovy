package dev.combination.dotnet

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class DotNetPublishTask extends DotNetBuildTask {
    private Boolean selfContained
    private String manifest

    @TaskAction
    public def run() {
        String[] args = getArgs()
        args[0] = "publish"
        if(selfContained == true)
            args += ["--self-contained", "true"]
        if(selfContained == false)
            args += ["--self-contained", "false"]
        if(manifest != null)
            args += ["--manifest", manifest]
	project.logger.info((["dotnet"] + args).join(' '))
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
