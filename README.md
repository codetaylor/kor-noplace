This mod provides a config to restrict blocks from being placed in dimensions.

##Dependencies
This mod requires CTKor Library. Please make sure it is installed along with this mod.

##Overview
Let's say you have a portal block that you want the player to have to place in the Nether. Simply add the block to the config file and whitelist dimension -1 and you're good to go.

##Configuration
A simple example configuration is provided:

```
{
  "blocks": {
    "minecraft:stone:0": {
      "dimensionIdWhiteList": [],
      "dimensionIdBlackList": [
        0
      ],
      "returnedItem": "minecraft:stone:0",
      "message": "As an example, stone has been blacklisted for dimension 0 (Overworld)."
    }
  }
}
```

The .json is pretty straightforward, but there are a couple of things to note here.

##Whitelist and Blacklist Logic
The whitelist and blacklist logic works as follows:
  1. If the whitelist is NOT empty and the placed block is in the whitelist for the dimension, it gets placed.
  2. If the blacklist is NOT empty and the placed block is NOT in the blacklist for the dimension, it gets placed.
  
Therefore, if you were to whitelist and blacklist the same dimension, the block would still be placed because the whitelist is checked first.

##Returned Item
If the returned item is left blank or omitted entirely, the mod will return to the player the item that is normally given when the block is broken. This works for most blocks, however, it does not work for blocks that return something different. Stone is a good example of this. When the stone block is broken, cobblestone is returned. The `returnedItem` parameter allows you to override the returned item, like we did in the example above.

##Message
If the message parameter is provided, and not left blank, the player will see this message when they try to place a restricted block. This serves to notify and inform the player, optionally giving hints as to where the block can be placed. Without this information the player might think that something is bugged or glitched.
