# Easy Update Suppression

This mod is a fork of [Better Update Suppression](https://modrinth.com/mod/better-update-suppression) that uses [Polymer](https://modrinth.com/mod/polymer) to make it server-side only.

## How to use?

1. Craft the `Update Suppression Block` using 1 Observer surrounded by 8 Redstone Dust.
   ![](img/-1.png)
2. Build the structure before update suppression (taking portal slicing as an example).
   ![](img/0.png)
3. Place the `Update Suppression Block` directly adjacent to the block that needs to be suppressed.
   ![](img/1.png)
4. Right-click the `Update Suppression Block`. The center gray part will turn red, indicating that it is active.
   ![](img/2.png)
5. Break the obsidian! Update suppression will be triggered, and the nether portal frames will not break.
   ![](img/3.png)
6. Right-click the `Update Suppression Block` again to deactivate it.
   ![](img/4.png)
7. Break the `Update Suppression Block`.
   ![](img/5.png)
8. Repeat the method to remove all obsidian.
   ![](img/6.png)

## Precautions
1. Update suppression can cause server crashes in mild cases, or world corruption in severe cases. **Make sure to back up your save before use!!! Very important!!!**
2. How to avoid world corruption and server crashes?
    - Enable update suppression crash protection in carpet mod.
    - Do not place two `Update Suppression Blocks` adjacent to each other, even if they are not activated.
    - Operate slowly; do not rush.

Dependencies: fabric-api, polymer