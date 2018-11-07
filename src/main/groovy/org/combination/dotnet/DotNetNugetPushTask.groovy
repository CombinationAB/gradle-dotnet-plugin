package org.combination.dotnet

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class DotNetNugetPushTask extends DotNetBaseTask {
    private String packagePath, source, symbolSource, apiKey, symbolApiKey
    private int timeout = -1
    private boolean noSymbols, disableBuffering

    @TaskAction
    public def run() {
        String[] args =  ["nuget", "push"]
        if(packagePath == null)
            throw new Exception("Package is mandatory")
        else
            args += packagePath
        if(noSymbols)
            args += "--no-symbols"
        if(disableBuffering)
            args += "--disable-buffering"
        if(source != null)
            args += ["--source", source]
        if(symbolSource != null)
            args += ["--symbol-source", symbolSource]
        if(apiKey != null)
            args += ["--api-key", apiKey]
        if(symbolApiKey != null)
            args += ["--symbol-api-key", symbolApiKey]
        if(timeout >= 0)
            args += ["--timeout", timeout]
        args += extraArgs
        exec args
    }

    public void source(String source) {
        this.source = source
    }

    public void packagePath(String packagePath) {
        this.packagePath = packagePath
    }

    public void packagePath(File packagePath) {
        this.packagePath = packagePath.absolutePath
    }

    public void symbolSource(String symbolSource) {
        this.symbolSource = symbolSource
    }

    public void apiKey(String apiKey) {
        this.apiKey = apiKey
    }

    public void symbolApiKey(String symbolApiKey) {
        this.symbolApiKey = symbolApiKey
    }

    public void noSymbols(boolean noSymbols) {
        this.noSymbols = noSymbols
    }

    public void disableBuffering(boolean disableBuffering) {
        this.disableBuffering = disableBuffering
    }

    public void timeout(int timeout) {
        this.timeout = timeout
    }

    public void selfContained(boolean selfContained) {
        this.selfContained = selfContained
    }
}