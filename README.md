Pymble 2026 Learning template repository

This repository is derived from [this
template](https://github.com/Dairy-Foundation/Templates/tree/teamcode-sloth).

I recommend checking out the [readme
branch](https://github.com/Dairy-Foundation/Templates/tree/readme).

Students are likely to have network issues building this repository, they will
need to connect to a hotspot or similar in order for many gradle related
downloads to go through.

To resync all branches with main, in git bash:
git for-each-ref --exclude="refs/remotes/origin/HEAD" --exclude="refs/heads" --exclude="refs/remotes/origin/master" --format="git switch %(refname:strip=-1) && git pull --rebase && git merge master --no-edit && git push" | sh

# MENTORS READ AND CONFIGURE BELOW THIS LINE

1. Make sure you pull before coding!
2. Sync Gradle while connected to Telstra681B
3. Do a single build while connected to Telstra681B (Hammer Icon)
4. If this is the first time uploading code to a Control Hub from THIS Laptop using THIS Driver Station, upload using the regular Run Configuration (Full Team Code Upload). Ensure you are connected to the robot wifi, and the ADB WiFi is connected to 192.168.43.1 in Android Studio.
5. Once the Full Team Code Upload is done, you can use Fast Load. Fast Load automatically connectes to 192.168.43.1 via adb wifi, uploads the code, and then disconnects ensuring we do not get stuck in the adb dead state previously experienced when the laptop would disconnect from the robot while adb was connected.

## Control Hubs & Driver Stations
Under the driver station, ensure:

### Settings
- Pairing Method > Control Hub
- Driver Station Layout > Landscape
- Robot Controller Name > TeamNumber-RC (Team number should be found somewhere, CANNOT BE TEAM NAME ANYMORE)
- Driver Station Name > TeamNumber-DS
> Start getting teams familiar with their team number as well so that they remember. See below for Team Numbers.

### Program & Manage
- Manage > Wifi Band > 5GHz
- Manage > Wifi Channel > auto (5GHz)
> Then Click (Apply WiFi Settings)

## Team Numbers
| Team Number  | Team Name |
| -------------: |:-------------|
|18363|RICE|
|18359|Pymble Kids|
|12554|Pymble Panthers|
|18362|Shark Beach|
|23875|Free Range Eggs|
|35924|Team Name|
|35958|SFC1872L|
|35971|Baked Not Fried|
|35978|Sausage Dog|
|35986|Shrek Steak|
|35992|(C) All Rights Reserved|
|35997|Built Different Gang|
|36003|Botzilla|
|36005|Entropy|
|36006|VIBS Hub|
|23636|Pymble Bees|
|18360|Pymble Pride|
