# DeepSea

*DeepSea* is a Fabric mod that adds configurable boat behavior in ocean biomes. All loaded boats are periodically checked every 100 ticks (5 seconds), and those in ocean biomes (`is_ocean`) and tagged with `deepsea:boat` will be broken and dropped.

## Compatibility

- **Required:** [Fabric API](https://modrinth.com/mod/fabric-api)
- **Made to work with:** [Small Ships](https://modrinth.com/mod/small-ships)

> By default, only `minecraft:boat` and `minecraft:chest_boat` are included in the `deepsea:boat` tag prior to 1.21.2. In versions 1.21.2 and above, only `#c:boats` is included.

## Installation

1. Drop the DeepSea `.jar` into your `mods` folder.
2. Start the server or load into a world — the mod is active immediately.

> This is a **server-side only** mod. Clients do **not** need to install it.

## Customization

To include/remove boats just create a datapack for the tag file:

**`data/deepsea/tags/entity_type/boat.json`**

Default for 1.21.1:

```json
{
  "values": [
    "minecraft:boat",
    "minecraft:chest_boat"
  ]
}
```

Default for 1.21.2 and above:
```json
{
  "values": [
    "#c:boats"
  ]
}
```