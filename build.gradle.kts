import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("java-library")
    id("net.minecrell.plugin-yml.bukkit") version "0.4.0"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = sourceCompatibility
}

version = "5.0.0"

repositories {
    mavenCentral()
    maven { url = uri("https://mvn.intellectualsites.com/content/groups/public/") }
    maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
    maven { url = uri("https://repo.dmulloy2.net/nexus/repository/public/") }
    maven { url = uri("https://maven.enginehub.org/repo/") }
}

dependencies {
    compileOnlyApi("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
    compileOnly("com.plotsquared:PlotSquared-Core:6.0.0-SNAPSHOT")
    compileOnly("com.plotsquared:PlotSquared-Bukkit:6.0.0-SNAPSHOT") { isTransitive = false }
    compileOnly("com.comphenix.protocol:ProtocolLib:4.6.0")
    compileOnly("com.sk89q.worldedit:worldedit-core:7.2.5")
    implementation("org.bstats:bstats-bukkit:2.2.1")
    implementation("org.bstats:bstats-base:2.2.1")
}

bukkit {
    name = "PlotHider"
    main = "com.plotsquared.plothider.PlotHiderPlugin"
    authors = listOf("Empire92", "dordsor21")
    apiVersion = "1.13"
    description = "Hide plots from other players"
    version = rootProject.version.toString()
    depend = listOf("ProtocolLib", "PlotSquared")
    website = "https://www.spigotmc.org/resources/20701/"

    permissions {
        register("plots.plothider.bypass") {
            default = BukkitPluginDescription.Permission.Default.FALSE
        }
    }
}

tasks.named<ShadowJar>("shadowJar") {
    archiveClassifier.set(null as String?)
    dependencies {
        relocate("org.bstats", "com.plotsquared.plothider.metrics") {
            include(dependency("org.bstats:bstats-base"))
            include(dependency("org.bstats:bstats-bukkit"))
        }
    }
    minimize()
}

tasks.named("build").configure {
    dependsOn("shadowJar")
}
