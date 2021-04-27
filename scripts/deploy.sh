#!/usr/bin/env bash

mvn clean package -D skipTests

echo 'Copy files...'

scp -P 2221 -i ~/.ssh/id_rsa \
    target/project-0.0.1-SNAPSHOT.jar \
    cadmin@46.235.74.211:/home/cadmin/garaj/

echo 'Restart server...'

ssh -i  ~/.ssh/id_rsa cadmin@46.235.74.211 -p 2221 << EOF

nohup java -jar /home/cadmin/garaj/project-0.0.1-SNAPSHOT.jar > log.txt &
EOF

echo 'Bye'