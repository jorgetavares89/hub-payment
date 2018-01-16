#!/bin/bash

echo "================== Container environment variables =================="
if [ "$ENV" = "dev-local" ]
then
    echo "ENV = $ENV"
    echo "DB_URL = $DB_URL"
    echo "DB_USER = $DB_USER"
    echo "DB_PASSWORD = $DB_PASSWORD"
    echo "HIBERNATE_DDL_AUTO = $HIBERNATE_DDL_AUTO"
    echo "JAVA_OPTS = $JAVA_OPTS"
    java -jar $JAVA_OPTS $1 \
                 --spring.datasource.url=$DB_URL \
                 --spring.datasource.username=$DB_USER \
                 --spring.datasource.password=$DB_PASSWORD \
		         --spring.jpa.hibernate.ddl-auto=$HIBERNATE_DDL_AUTO
fi