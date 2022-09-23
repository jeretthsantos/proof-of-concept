import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val jaxws by configurations.creating
val wsimportGeneratedSourceDir = file("${buildDir}/generated/sources/ws")

plugins {
    id("org.springframework.boot") version "2.7.2"
    id("io.spring.dependency-management") version "1.0.12.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

group = "dev.jeretth.santos.poc"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
    jaxws
}

sourceSets {
    main {
        java {
            srcDirs("src/main/java", wsimportGeneratedSourceDir)
        }
    }
}

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2021.0.3"
extra["jaxwsVersion"] = "2.3.5"

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.github.openfeign:feign-soap:11.9.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-web-services")
    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
//    implementation("io.github.resilience4j:resilience4j-spring-boot2")
//    implementation("org.springframework.boot:spring-boot-starter-aop")

    // Do not upgrade unless you're moving to Jakarta EE
    implementation("com.sun.xml.messaging.saaj:saaj-impl:1.5.3")
    implementation("com.sun.xml.ws:rt:2.3.1")


    // Do not upgrade unless you're moving to Jakarta EE
    jaxws("com.sun.xml.ws:jaxws-rt:${property("jaxwsVersion")}")
    jaxws("com.sun.xml.ws:jaxws-tools:${property("jaxwsVersion")}")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.create("generateWSDL") {
    val wsdlDir = "${projectDir}/src/main/resources/wsdl"
    val wsdlLocation = layout.files(file(wsdlDir).listFiles()).filter { it.name.endsWith(".wsdl") }

    doLast {
        ant.withGroovyBuilder {
            wsimportGeneratedSourceDir.mkdirs()
            "taskdef"(
                "name" to "wsimport",
                "classname" to "com.sun.tools.ws.ant.WsImport",
                "classpath" to jaxws.asPath
            )

            wsdlLocation.forEach {
                println("\nGenerating wsdl: $it")
                "wsimport"(
                    "wsdl" to it,
                    "sourcedestdir" to wsimportGeneratedSourceDir,
                    "encoding" to "UTF-8",
                    "keep" to true,
                    "extension" to true,
                    "xadditionalHeaders" to true,
                    "xnocompile" to true
                )
            }
        }
    }
}

tasks.withType<KotlinCompile> {
    dependsOn("generateWSDL")
    source(wsimportGeneratedSourceDir)
}