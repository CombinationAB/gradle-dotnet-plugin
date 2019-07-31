package dev.combination.dotnet

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.Internal

class DotNetSonarScannerTask extends DotNetBaseTask {
    private boolean selfContained
    private String command, key, version, organization
    private HashMap<String, String> props = new HashMap<String, String>()

    @Internal
    protected String[] getArgs() {
        def args = ["sonarscanner", command]
        if(key)
            args += "/k:${key}"
        if(version)
            args += "/v:${version}"
        if(organization)
            args += "/o:${organization}"
		props.each{ k, v -> args += "/d:${k}=${v}" }
        args += extraArgs
		project.logger.info((["dotnet"] + args).join(' '))
        return args
    }

    @TaskAction
    public def run() {
        exec getArgs()
    }

    public void command(String command) {
        this.command = command
    }

    public void key(String key) {
        this.key = key
    }

    public void version(String version) {
        this.version = version
    }

    public void organization(String organization) {
        this.organization = organization
    }

    public void login(String token) {
        prop("sonar.login", token)
    }

    public void password(String password) {
        prop("sonar.password", password)
    }

    public void prop(String key, String value) {
        props.put(key, value)
    }
}