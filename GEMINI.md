## Gemini Added Memories
- Minecraft `26.1` is the first Minecraft version to be released unobfuscated. Do not specify mappings like Yarn or Mojang mappings in `build.gradle` dependencies.
- Fabric Loom `1.15.+` changed the plugin ID to `net.fabricmc.fabric-loom` for these versions. Use standard Gradle `implementation` instead of `modImplementation`.
- Fabric API dropped the `"fabric"` mod ID in `fabric.mod.json` `depends` section. It must be `"fabric-api"` for 26.1+.
- Java 25 is required for Minecraft 26.1+.
- Many Fabric API packages and methods were renamed to align with official Mojang mappings (e.g., `ItemGroupEvents` -> `CreativeModeTabEvents`, `FabricDataOutput` -> `FabricPackOutput`).