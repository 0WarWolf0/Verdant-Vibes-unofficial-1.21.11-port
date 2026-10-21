# Verdant Vibes – Minecraft 1.21.11 (Fabric) port

Port of **Verdant Vibes 1.0.1 for 1.20.1** (by Pandarix, MIT) to **Minecraft 1.21.11 / Fabric**, using Yarn mappings.

## Build without installing anything (GitHub)

1. Create a free GitHub account and a new empty repository.
2. Upload everything from this folder (Add file > Upload files; drag the contents in, including `.github`).
   If the `.github` folder is skipped, use Add file > Create new file, name it `.github/workflows/build.yml`
   and paste in the file of that name from this project.
3. Open the **Actions** tab, wait for "Build mod jar" to finish (a few minutes), open the run,
   and download **verdantvibes-1.21.11-jar** at the bottom. Unzip it to get the mod `.jar`.
4. If the run fails, open it and copy the red error text; that's all that's needed to fix it.

## Build locally

Requires **JDK 21**.

```
./gradlew build          # Windows: gradlew.bat build
```

The finished mod is `build/libs/verdantvibes-1.0.1-1.21.11.jar` (ignore the `-sources` jar).
Drop it in `mods/` together with **Fabric Loader >= 0.17.3** and **Fabric API for 1.21.11**.

> **Status: not compiled or run.** This port was written without access to Minecraft, Loom or the
> Fabric Maven, so every API call was checked by hand against the 1.21.11 Yarn mappings and the
> Fabric API 0.141.x sources, but it has never been through a compiler. Expect to possibly fix a
> small compile error or two on the first build, and please test in-game.

## Where this came from

The public GitHub repo (Pandarix/VerdantVibes) only contains the Forge versions, so the Java code was
rebuilt from the Fabric 1.20.1 jar (decompiled and remapped to Yarn), cross-checked against the Forge source.
Textures, models, blockstates, lang, structures and other data were taken from the jar unchanged and
converted to the new folder/format layout.

## What changed for 1.21.11

* Gradle/Loom setup (Loom 1.19 remap plugin, Yarn, Java 21), `fabric.mod.json` dependencies.
* `Identifier` constructors -> `Identifier.of`; block/item registration now sets a registry key on
  `Block.Settings` / `Item.Settings` (required since 1.21.2); `FabricBlockSettings`/`FabricItemSettings` removed.
* Block overrides updated to current signatures (`onUse`, `onEntityCollision` with `EntityCollisionHandler`,
  `getModelOffset(pos)`, `Fertilizable`, codecs).
* `Block#appendTooltip` no longer exists, so the Latin plant-name tooltip is added via Fabric API's `ItemTooltipCallback`.
* Pot-planting sound moved from `onStateReplaced` to `onBlockAdded` (the old hook no longer receives the new state).
* Villager profession/trade/POI code updated (`VillagerProfession` record, `TradedItem`, 3-arg trade factory).
* Render layer via `BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT, ...)`.
* Tree decorator uses `MapCodec` and is now registered as `verdantvibes:trunk_ivy` (was `minecraft:trunk_ivy`).
* Worldgen features (`configured_feature`/`placed_feature`) ship as plain JSON; the datagen entrypoint was dropped.
* Data folders renamed (`loot_table`, `recipe`, `advancement`, `structure`, `tags/block`, `tags/item`);
  recipe results/advancement icons use the `id` format; item models added under `assets/verdantvibes/items/`.

## Known uncertainties

1. **High-altitude candy tuft biome tag.** The 1.20.1 jar used a biome tag here that could not be
   identified from the compiled code. This port uses `BiomeTags.IS_MOUNTAIN` (the same as the normal candy tuft).
   Change it in `worldgen/ModPlantGeneration.java` if you want different spawning.
2. **Pot interaction order.** In 1.21.x, `onUseWithItem` runs before `onUse`, so sneak-rotating a pot
   while holding a plant item may place the plant instead of turning the pot. Sneak-click with an empty hand works as before.
3. **Yarn version.** `yarn_mappings` in `gradle.properties` uses `1.21.11+build.+` (newest build). Pin it if you like.

## License

MIT, as the original. Original mod by Pandarix: https://github.com/Pandarix/VerdantVibes
