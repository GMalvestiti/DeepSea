# DeepSea

*DeepSea* is a Fabric mod that adds configurable boat behavior in ocean biomes. All loaded boats are periodically checked every 100 ticks (5 seconds), and those in ocean biomes (`is_ocean`) and tagged with `deepsea:boat` will be broken and dropped.

## Compatibility

- **Required:** [Fabric API](https://modrinth.com/mod/fabric-api)
- **Made to work with:** [Small Ships](https://modrinth.com/mod/small-ships)

> By default, only `#c:boats` is included in the `deepsea:boat` tag.

## Installation

1. Drop the DeepSea `.jar` into your `mods` folder.
2. Start the server or load into a world — the mod is active immediately.

> This is a **server-side only** mod. Clients do **not** need to install it.

## Customization

To include/remove boats just create a datapack for the tag file:

**`data/deepsea/tags/entity_type/boat.json`**

```json
{
  "values": [
    "#c:boats"
  ]
}
```

## Documentation

- **enabled**:<br>
  Enables or disables the mod.<br>
  Type: `boolean` Default: `true`
- **tick_interval**:<br>
  The interval in ticks between checks for boats.<br>
  Type: `integer` Default: `100`
- **boat_damage**:<br>
  The damage amount applied to boats in ocean biomes.<br>
  Type: `float` Default: `100.0`
- **cache_size**:<br>
  The maximum number of biome lookup results (e.g., "is this position in an ocean?") to cache before being refreshed.<br>
  Type: `integer` Default: `500`
- **cache_time**:<br>
  The duration in minutes that cached biome results are kept before being refreshed.<br>
  Type: `integer` Default: `240`

## Full Configuration Example:

```json
{
  "enabled": true,
  "tick_interval": 100,
  "boat_damage": 100.0,
  "cache_size": 500,
  "cache_time": 240
}
```
