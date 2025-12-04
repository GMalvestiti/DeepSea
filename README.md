# DeepSea

*DeepSea* is a Fabric mod that adds configurable boat behavior in ocean biomes. All loaded boats are periodically checked every 100 ticks (5 seconds ) by default, and those in the configured ocean biomes (`#deepsea:ocean`) and tagged with `#deepsea:boat` will be broken and dropped.

## Compatibility

- **Required:** [Fabric API](https://modrinth.com/mod/fabric-api)
- **Made to work with:** [Small Ships](https://modrinth.com/mod/small-ships)

> By default, only `#c:boats` is included in the `#deepsea:boat` tag and `#c:is_ocean` in the `#deepsea:ocean`.

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

To include/remove biomes just create a datapack for the tag file:

**`data/deepsea/tags/worldgen/biome/ocean.json`**

```json
{
  "values": [
    {
      "id": "#c:is_ocean",
      "required": false
    }
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
- **discard_boat**:<br>
  If true, boats in ocean biomes are discarded without dropping the boat item, if false, boats take damage, break and drop the boat item.<br>
  Type: `boolean` Default: `false`
- **cache**:<br>
  Configuration for biome lookup caching.<br>
  Type: `object` Default: see below
  - **cache_size**:<br>
    The maximum number of biome lookup results (e.g., "is this position in an ocean?") to cache before being refreshed.<br>
    Type: `integer` Default: `500`
  - **cache_time**:<br>
    The duration in minutes that cached biome results are kept before being refreshed.<br>
    Type: `integer` Default: `240`
- **sound**:<br>
  Configuration for sound effects when boats are damaged or destroyed.<br>
  Type: `object` Default: see below
  - **play_sound**:<br>
    Whether to play a sound effect when a boat is damaged or destroyed.<br>
    Type: `boolean` Default: `true`
  - **volume**:<br>
    The volume of the sound effect played when a boat is damaged or destroyed.<br>
    Type: `float` Default: `1.0`
  - **pitch**:<br>
    The pitch of the sound effect played when a boat is damaged or destroyed.<br>
    Type: `float` Default: `1.0`

## Full Configuration Example:

```json
{
  "enabled": true,
  "tick_interval": 100,
  "boat_damage": 100.0,
  "discard_boat": false,
  "cache": {
    "cache_size": 500,
    "cache_time": 240
  },
  "sound": {
    "play_sound": true,
    "volume": 1.0,
    "pitch": 1.0
  }
}
```
