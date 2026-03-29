<div align="center">

# Minecraft-Plugins ⛏️

[![Typing SVG](https://readme-typing-svg.demolab.com?font=Figtree&pause=1000&color=FF5555&center=true&width=700&lines=My+personal+collection+of+PaperMC+plugins.;Challenge+mechanics%2C+hardcore+ideas%2C+and+server-side+experiments.;Built+for+Minecraft+servers+with+Java+and+Paper.)](https://git.io/typing-svg)

![Java](https://img.shields.io/badge/Java-21-orange)
![PaperMC](https://img.shields.io/badge/PaperMC-plugin-grey)
![Gradle](https://img.shields.io/badge/Gradle-build-02303A)
[![Current Version](https://img.shields.io/github/v/release/TaoTDM/Minecraft-Plugins?label=current%20version&color=brightgreen)](https://github.com/TaoTDM/Minecraft-Plugins/releases)


</div>

This repository is my personal monorepo for Minecraft Paper plugins.

Each plugin lives in its own folder as a standalone Gradle project, so I can build, test, and maintain plugins independently while keeping everything organized in one place.

---

## 📁 Structure

```
Minecraft-Plugins/
├── README.md
├── PluginName/                  ← Plugin folder
│   ├── src/main/java/           ← Plugin source code
│   ├── src/main/resources/      ← plugin.yml, configs
│   ├── build.gradle             ← Build script
│   ├── gradlew                  ← Build runner
│   └── gradle/                  ← Gradle wrapper
└── .gitignore
```

---


## Plugins

Each plugin has its own dedicated folder and README.

- [OneLifeMC](https://github.com/TaoTDM/Minecraft-Plugins/blob/main/OneLifeMC/README.md) — Shared-life hardcore Paper plugin with world reset mechanics and instant respawn support.

More plugins will be added here over time.

---

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
git clone https://github.com/TaoTDM/plugin-template.git NewPlugin
rm -rf NewPlugin/.
```

