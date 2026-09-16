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
