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
> if one player dies, **everyone dies.**

---

## ❤️ Features

- ☠️ Shared-death gameplay — one death ends it for everyone
- 👻 All players switch to spectator mode simultaneously on death
- 📍 Every player teleports to the death location when triggered
- 🧱 Server-side only, no client mod needed
  
---

## 🎮 How It Works

When any player dies:
1. The plugin listens for `PlayerDeathEvent`
2. The exact death location is **captured immediately** when the event fires
3. After a short delay (1 second) to allow death to fully process
4. Every online player — including the one who died — is switched to **Spectator mode**
5. Every online player is **teleported to the exact death location**

---

## ⚙️ Server Setup

To enable hardcore mode, set the following in your `server.properties`:

```properties
hardcore=true
gamemode=survival
```

Restart the server after making changes.

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
