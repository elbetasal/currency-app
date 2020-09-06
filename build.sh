#!/usr/bin/env bash

set -e

mvn -Dmaven.test.failure.ignore=true clean package -f back-end/pom.xml
