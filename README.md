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

## Full Configuration Example:

```json
{
  "enabled": true,
  "tick_interval": 100
}
```