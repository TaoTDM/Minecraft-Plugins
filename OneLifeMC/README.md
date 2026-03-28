# TaoTDM Minecraft Plugins

This repository is my personal collection of PaperMC plugins for Minecraft servers. Each plugin lives in its own subfolder as a complete, independent Gradle project.

## 📁 Structure

```
Minecraft-Plugins/
├── README.md
├── PluginName/                      ← Plugin folder
│   ├── src/main/java/           ← Plugin source code
│   ├── src/main/resources/      ← plugin.yml, configs
│   ├── build.gradle             ← Build script
│   ├── gradlew                  ← Build runner
│   └── gradle/                  ← Gradle wrapper
└── .gitignore
```

## 🚀 How to Use

### Building a Plugin
```bash
cd PluginName
./gradlew shadowJar
```
Output jar: `build/libs/PluginName-1.0.0.jar`

### Running the Server
1. Download Paper jar from [papermc.io](https://papermc.io)
2. Drop plugin `.jar` in `plugins/` folder
3. Start server: `java -jar paper-1.21.4.jar --nogui`

### Adding a New Plugin
1. Clone the template inside the repo:
```bash
git clone https://github.com/CrimsonWarpedcraft/plugin-template.git NewPlugin
rm -rf NewPlugin/.

Sources
