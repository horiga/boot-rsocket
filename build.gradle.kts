import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

extra["kotlin.version"] = "1.4.20"

plugins {
	id("idea")
	id("java")

	id("org.springframework.boot") version "2.3.7.RELEASE"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"

	kotlin("jvm") version "1.4.20"
	kotlin("plugin.noarg") version "1.4.20"
	kotlin("plugin.spring") version "1.4.20"
}

group = "org.horiga.study"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

val ktlint: Configuration by configurations.creating

noArg {
    annotation("org.horiga.study.rsocket.NoArgs")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-rsocket")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation("io.micrometer:micrometer-registry-prometheus:1.6.2")
    implementation("io.rsocket:rsocket-micrometer:1.1.0")

	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

	runtimeOnly("org.springframework.boot:spring-boot-devtools")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("io.projectreactor:reactor-test")

    ktlint("com.pinterest:ktlint:0.39.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "11"
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.getByName<BootBuildImage>("bootBuildImage") {
    imageName = "org.horiga.study/spring-boot/${project.name}:latest"
}

tasks.register<JavaExec>("ktlint") {
    group = "verification"
    description = "Kotlin code style check with ktlint."
    classpath = configurations.getByName("ktlint")
    main = "com.pinterest.ktlint.Main"
    args = listOf("--reporter=plain","--reporter=checkstyle,output=${buildDir}/reports/ktlint/ktlint-report.xml", "src/main/**/*.kt")
}
