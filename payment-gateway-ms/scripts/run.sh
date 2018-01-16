#!/bin/bash

echo "================== Container environment variables =================="
if [ "$ENV" = "dev-local" ]
then
    echo "ENV = $ENV"
    java -jar $JAVA_OPTS $1
else
    java -jar $1
fi
