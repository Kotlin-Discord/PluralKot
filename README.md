# PluralKot

[![Discord: Click here](https://img.shields.io/static/v1?label=Discord&message=Click%20here&color=7289DA&style=for-the-badge&logo=discord)](https://discord.gg/gjXqqCS) [![Release](https://img.shields.io/nexus/r/com.kotlindiscord.pluralkot/PluralKot?nexusVersion=3&logo=gradle&color=blue&label=Release&server=https%3A%2F%2Fmaven.kotlindiscord.com&style=for-the-badge)](https://maven.kotlindiscord.com/#browse/browse:maven-releases:com%2Fkotlindiscord%2Fpluralkot%2FPluralKot) [![Snapshot](https://img.shields.io/nexus/s/com.kotlindiscord.pluralkot/PluralKot?logo=gradle&color=orange&label=Snapshot&server=https%3A%2F%2Fmaven.kotlindiscord.com&style=for-the-badge)](https://maven.kotlindiscord.com/#browse/browse:maven-snapshots:com%2Fkotlindiscord%2Fpluralkot%2FPluralKot)

This project is a simple API wrapper around the [PluralKit API](https://pluralkit.me/api/). It's intended to take the
low-level work out of programmatic access for PluralKit data.

## Getting Started

* **Maven repo:** `https://maven.kotlindiscord.com/repository/maven-public/`
* **Maven coordinates:** `com.kotlindiscord.pluralkot:PluralKot:VERSION`

## Usage

This library simply abstract the routes provided by the [PluralKit API](https://pluralkit.me/api/). Take a look at
the documentation comments in the PluralKit class for in-depth information, or make use of your IDE's autocomplete
for now.

```kotlin
suspend fun main() {
    val pk = PluralKit(SYSTEM_KEY)
    val mySystem = pk.system()

    println("My system: ${mySystem.id}")
}
```
