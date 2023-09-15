# gradle-enterprise-plugin-test-distribution-issue

Given:

```
dependencies {
    implementation gradleApi()
    compileOnly 'com.gradle:gradle-enterprise-gradle-plugin:3.15'
    testImplementation 'com.gradle:gradle-enterprise-gradle-plugin:3.15'
    testImplementation 'org.junit.jupiter:junit-jupiter:5.10.0'
}


tasks.withType(Test).configureEach {
    useJUnitPlatform()
    distribution {
        enabled = true
        remoteExecutionPreferred = false
        maxRemoteExecutors = 0
    }
    testLogging {
        displayGranularity = 0
        events('PASSED', 'FAILED', 'SKIPPED')
    }
}
```

when running `./gradlew build` results in:

```
* What went wrong:
Execution failed for task ':test'.
> Failed to fork test JVM
  Standard error from JVM:
      Terminating due to fatal error
      java.lang.IllegalAccessError: failed to access class com.gradle.enterprise.testdistribution.launcher.a.a.a from class com.gradle.enterprise.testdistribution.launcher.forked.l (com.gradle.enterprise.testdistribution.launcher.a.a.a and com.gradle.enterprise.testdistribution.launcher.forked.l are in unnamed module of loader 'app')
        at com.gradle.enterprise.testdistribution.launcher.forked.l.a(SourceFile:19)
        at com.gradle.enterprise.testdistribution.launcher.forked.LauncherMain.main(SourceFile:57)


* Try:
```

In addition, when using an old version of the plugin as dependency

```
dependencies {
implementation gradleApi()
compileOnly 'com.gradle:gradle-enterprise-gradle-plugin:3.10.1'
testImplementation 'com.gradle:gradle-enterprise-gradle-plugin:3.10.1'
testImplementation 'org.junit.jupiter:junit-jupiter:5.10.0'
}

```

the build fails with:

```
Execution failed for task ':test'.
> Failure during test discovery: Forked test JVM terminated unexpectedly with exit value 1
  Standard error from JVM:
      Terminating due to fatal error
      java.lang.IncompatibleClassChangeError: Method 'com.gradle.enterprise.a.b.a com.gradle.enterprise.a.b.b.c()' must be InterfaceMethodref constant
        at com.gradle.enterprise.testdistribution.launcher.forked.i.<clinit>(SourceFile:30)
        at com.gradle.enterprise.testdistribution.launcher.forked.LauncherMain.a(SourceFile:65)
        at com.gradle.enterprise.testdistribution.launcher.forked.l.a(SourceFile:20)
        at com.gradle.enterprise.testdistribution.launcher.forked.LauncherMain.main(SourceFile:57)


* Try:
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Get more help at https://help.gradle.org.
```