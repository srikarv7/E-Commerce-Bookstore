#!/bin/bash

sleep 30

sudo apt-get update
sudo apt-get upgrade -y

sudo apt-get install openjdk-11-jdk -y
####java -jar application-0.0.1-SNAPSHOT.jar

##Installing mysql server

echo "sudo apt-get install mysql-server -y"
sudo apt-get install mysql-client -y
echo "####starting the service####"
sudo systemctl start mysql.service
echo "####checking status of the server######"
sudo systemctl status mysql.service

sudo chmod 777 application-0.0.1-SNAPSHOT.jar

ls -lha


sleep 60

# Making sure that the app keeps running. We will use system d

sudo mv /tmp/application.service /etc/systemd/system/application.service

sudo systemctl enable application.service
sudo systemctl start application.service
sudo systemctl status application.service
