#!/usr/bin/env bash
echo "creating cxf_one schema"
mvn liquibase:update -Denv=cxf_one
echo "creating rest_one schema"
mvn liquibase:update -Denv=rest_one
echo "creating axis_one schema"
mvn liquibase:update -Denv=axis_one
echo "create schemas finished"