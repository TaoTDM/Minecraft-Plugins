<div align="center">

# OneLifeMC ❤️

[![Typing SVG](https://readme-typing-svg.demolab.com?font=Figtree&pause=1000&color=FF5555&center=true&width=700&lines=One+life.+One+team.+One+mistake.;If+one+player+dies%2C+everyone+dies.;A+shared-life+hardcore+challenge+for+PaperMC.)](https://git.io/typing-svg)

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=flat&logo=openjdk&logoColor=white)
![PaperMC](https://img.shields.io/badge/PaperMC-plugin-ffffff?style=flat)
![Minecraft](https://img.shields.io/badge/Minecraft-hardcore_mode-AA0000?style=flat)
[![Current Version](https://img.shields.io/github/v/release/TaoTDM/Minecraft-Plugins?label=current%20version&color=brightgreen)](https://github.com/TaoTDM/Minecraft-Plugins/releases)

</div>

> **❤️ Shared-life hardcore for multiplayer worlds.**
>
> OneLifeMC is a Paper plugin where the whole server shares one fate:
> if one player dies, **the world resets — for everyone.**

---

## ❤️ Features

- ☠️ Shared-death gameplay — one death wipes the world for everyone
- 🌍 Automatic world reset and fresh generation on any player death
- ⏱️ Configurable respawn/reset delay via `/setrespawndelay`
- ⚡ Instant respawn via `doImmediateRespawn` applied to all worlds automatically
- 🧱 Server-side only — no client mod needed
- 🗺️ Designed for Paper 1.21.x

---

## 🎮 How It Works

When any player dies:

1. The plugin listens for `PlayerDeathEvent`
2. The respawn/reset delay countdown begins (configurable via `/setrespawndelay`)
3. `GameRule.DO_IMMEDIATE_RESPAWN` is applied to all loaded worlds — and any worlds loaded afterward
4. The world is **wiped and a fresh one is generated**
5. Everyone starts over — together

---

## 🧰 Requirements

- **Java 21**
- **PaperMC 1.21.x** or a compatible Paper fork — [download here](https://papermc.io/downloads/paper)
- A permission plugin (optional) to control access to `/setrespawndelay`

---

## 🔑 Permissions

### `onelifemc.setrespawndelay`

Grants access to the `/setrespawndelay` command for a player or console operator.

Players **without** this permission will see:
```
You do not have permission to use this command.
```

To grant it with LuckPerms:
```bash
lp user <name> permission set onelifemc.setrespawndelay true
```

Or add it to an admin group in your permission plugin of choice.

---

## 💬 Commands

### `/setrespawndelay <seconds>`

Sets the respawn/reset delay in seconds.

```
/setrespawndelay 30
```

Requires: `onelifemc.setrespawndelay`

---

## 📦 Installation

1. Download the latest release `.zip` from the [GitHub Releases](../../releases) page
2. Unzip and place the `.jar` into your server's `plugins/` folder
3. Start or restart the server
4. Confirm `OneLifeMC` appears in `/plugins`

---

## 🔨 Building from Source

### Mac / Linux

```bash
./gradlew shadowJar
```

The built JAR will be in `build/libs/` — copy it to your server's `plugins/` folder.

### Windows

```powershell
.\gradlew.bat shadowJar
```

The built JAR will be in `build\libs\` — copy it to your server's `plugins\` folder.

> If you're not using Shadow, replace `shadowJar` with `build`.

---

## ⚙️ Configuration

Configuration is currently handled at runtime via commands. The plugin automatically:

- Applies `GameRule.DO_IMMEDIATE_RESPAWN = true` to **all loaded worlds**
- Re-applies the same gamerule to any worlds loaded **after startup**
- Persists the respawn/reset delay set via `/setrespawndelay`

A future version may introduce a `config.yml` for options such as default delay, custom messages, and feature toggles.
