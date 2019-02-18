package org.combination.dotnet

import org.gradle.api.Plugin
import org.gradle.api.Project

class DotNetPlugin implements Plugin<Project> {
    void apply(Project target) {
        target.task('dotnetBuild', type: DotNetBuildTask)
        target.task('dotnetMsbuild', type: DotNetMsbuildTask)
        target.task('dotnetNugetPush', type: DotNetNugetPushTask)
        target.task('dotnetPack', type: DotNetPackTask)
        target.task('dotnetPublish', type: DotNetPublishTask)
        target.task('dotnetRestore', type: DotNetRestoreTask)
        target.task('dotnetTest', type: DotNetTestTask)
        target.task('dotnetSonarScanner', type: DotNetSonarScannerTask)
    }
}