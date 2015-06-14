#!/bin/bash

cd ../../stock-iface
mvn clean install
cd ..
mvn clean install
cp -r stock-iface/target/* target/
mvn exec:java -P stock