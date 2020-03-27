package dev.combination.dotnet

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.Internal

class DotNetSwaggerToFileTask extends DotNetBaseTask {
    private String output, startupassembly, swaggerdoc

    @Internal
    protected String[] getArgs() {
        def args = ["swagger tofile"]
        if(output)
            args += ["--output", output]
        if(startupassembly)
            args += startupassembly
        if(swaggerdoc)
            args += swaggerdoc
        project.logger.info((["dotnet"] + args).join(' '))
        return args
    }

    @TaskAction
    public def run() {
        exec getArgs()
    }

    public void output(String output) {
        this.output = output
    }

    public void swaggerdoc(String swaggerdoc) {
        this.swaggerdoc = swaggerdoc
    }

    public void startupassembly(String startupassembly) {
        this.startupassembly = startupassembly
    }
}