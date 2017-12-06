package org.combination.dotnet

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class DotNetMsbuildTask extends DotNetBaseTask {
    private def args = []

    @TaskAction
    public def dotnetMsbuild() {
        verbosity = null
        exec args
    }

    public void args(String[] args) {
        this.args = args
    }

    public void arg(String arg) {
        this.args += arg
    }
}