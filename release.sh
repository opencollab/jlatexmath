#!/bin/bash
RELEASE_VERSION=$1
GPG_PASSPHRASE=$2
mvn --batch-mode release:prepare \
    -Dtag=$RELEASE_VERSION \
    -DreleaseVersion=$RELEASE_VERSION \
    -DdevelopmentVersion=$RELEASE_VERSION.1 \
    -DautoVersionSubmodules=true \
    -s ~/.m2/settings-external.xml \
    -Darguments=-Dgpg.passphrase=$GPG_PASSPHRASE && \
mvn --batch-mode release:perform \
    -s ~/.m2/settings-external.xml \
    -Darguments=-Dgpg.passphrase=$GPG_PASSPHRASE

