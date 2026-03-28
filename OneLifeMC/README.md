<div align="center">

# OneLifeMC ❤️

[![Typing SVG](https://readme-typing-svg.demolab.com?font=Figtree&pause=1000&color=FF5555&center=true&width=700&lines=One+life.+One+team.+One+mistake.;If+one+player+dies%2C+everyone+dies.;A+shared-life+hardcore+challenge+for+PaperMC.)](https://git.io/typing-svg)

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=flat&logo=openjdk&logoColor=white)
![PaperMC](https://img.shields.io/badge/PaperMC-plugin-ffffff?style=flat)
![Minecraft](https://img.shields.io/badge/Minecraft-hardcore_mode-AA0000?style=flat)

</div>

> **❤️ Shared-life hardcore for multiplayer worlds.**
>
> OneLifeMC is a Paper plugin where the whole server shares one fate:
> if one player dies, **everyone dies**.

---

## ❤️ Features

- ☠️ Shared-death gameplay
- 👻 Spectator players excluded from the effect
- 🧱 Server-side only, no client mod needed
- ⚡ Lightweight Paper plugin
- 🎮 Perfect for hardcore co-op challenge runs

---

## 🎮 How It Works

When any player dies:

1. The plugin listens for `PlayerDeathEvent`
2. It checks all online players
3. Every other online non-spectator player is killed

---

## 🧰 Requirements

- Java 21
- PaperMC server
- Compatible Minecraft / Paper version matching `plugin.yml`

---

## 📦 Build

From inside the `OneLifeMC` folder:

```bash
./gradlew shadowJar
```
