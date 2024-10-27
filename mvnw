#!/bin/bash
# -----------------------------------------------------------------------------
# Maven Wrapper for Unix/Linux
# -----------------------------------------------------------------------------

MVN_VERSION="3.8.1" # Specify the desired Maven version
MVNW_REPO="${HOME}/.m2/wrapper/dists"

# Stop on errors
set -e

# Determine base directory of the script
BASE_DIR=$(dirname "$0")

# Download Maven if not already installed
download_maven() {
    local downloadUrl="https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/${MVN_VERSION}/apache-maven-${MVN_VERSION}-bin.zip"
    local zipFile="${MVNW_REPO}/apache-maven-${MVN_VERSION}-bin.zip"

    # Download Maven binary if it does not exist
    if [ ! -f "$zipFile" ]; then
        echo "Downloading Maven $MVN_VERSION..."
        mkdir -p "${MVNW_REPO}/apache-maven-${MVN_VERSION}"
        curl -o "$zipFile" "$downloadUrl"
        unzip -q -d "${MVNW_REPO}/apache-maven-${MVN_VERSION}" "$zipFile"
    fi
}

# Run Maven with provided arguments
exec_maven() {
    MAVEN_HOME="${MVNW_REPO}/apache-maven-${MVN_VERSION}"
    export PATH="${MAVEN_HOME}/bin:$PATH"
    mvn "$@"
}

# Ensure Maven is downloaded, then execute
download_maven
exec_maven "$@"