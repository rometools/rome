#!/bin/bash

set -e

if [ "$#" -ne 2 ]; then
    echo "Usage: release <releaseVersion> <nextVersion>"
    exit 1
fi

releaseVersion=$1
nextVersion=$2

./mvnw versions:set -DgenerateBackupPoms=false -DnewVersion=$releaseVersion
./mvnw scm:checkin -DpushChanges=false "-Dmessage=Update version to $releaseVersion"
./mvnw scm:tag -DpushChanges=false -Dtag=$releaseVersion
./mvnw versions:set -DgenerateBackupPoms=false -DnewVersion=$nextVersion
./mvnw scm:checkin -DpushChanges=false "-Dmessage=Update version to $nextVersion"
./mvnw scm:checkin
