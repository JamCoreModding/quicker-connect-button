# Quicker Connect Button

<center>
    <img alt="forge" height="25" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/compact/supported/forge_vector.svg">
    <img alt="fabric" height="25" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/compact/supported/fabric_vector.svg">
    <img alt="quilt" height="25" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/compact/supported/quilt_vector.svg">
</center>

Adds a connect button to the main menu for quickly connecting to servers.

<center>

![Screenshot 1](./screenshot/screenshot_1.png)

![Screenshot 2](./screenshot/screenshot_2.png)

</center>

## Configuration

The config can either be changed through a GUI (using Mod Menu on Fabric or the config button on Forge), or directly at `config/quickerconnectbutton.json5`. The GUI contains tooltips explaining each option.

```json5
{
  /* Leave empty to disable the quick connect button
     - default: \"\"
  */
  ip: "localhost",
  /* - default: 25565
     - must be between 0.0 and 65535.0
  */
  port: 25565,
  /* Whether to replace the default multiplayer button on the main menu, or add a new one to its right.
     - default: false
  */
  replaceMultiplayerButton: true,
  /* The text to display on the button. Leave empty to use "Connect".
     - default: \"\"
  */
  text: "Connect to an Awesome Server",
  /* What to do if the server has a resource pack.
     - default: PROMPT
     - must be one of: ENABLED, DISABLED, PROMPT
  */
  resourcePackBehaviour: "ENABLED"
}
```

<center><a href="https://bisecthosting.com/jamalam"><img src="https://www.bisecthosting.com/partners/custom-banners/982884df-e307-4b8d-b8c2-9f1868a1f13a.webp" height="120"></a></center>
