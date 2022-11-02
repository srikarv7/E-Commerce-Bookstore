#!/bin/bash

sleep 30

sudo apt-get update
sudo apt-get upgrade -y
#sudo apt-get install nginx -y
#sudo apt-get clean

sudo apt-get install openjdk-11-jdk -y
####java -jar application-0.0.1-SNAPSHOT.jar

##Installing mysql server

echo "sudo apt-get install mysql-server -y"
sudo apt-get install mysql-client -y
echo "####starting the service####"
sudo systemctl start mysql.service
echo "####checking status of the server######"
sudo systemctl status mysql.service
#echo "###creating password root######"
#sudo mysql -u root -proot -e "ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';"

#sudo apt install mysql-server -y
#sudo mysql -u root -e "ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';"

#sudo variable1=$(echo "ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root'; exit" | sudo mysql)
#sudo variable1=$(echo "ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root'; exit" | sudo mysql)

#Modifying the permission of the application jar
sudo chmod 777 application-0.0.1-SNAPSHOT.jar

ls -lha


sleep 60

# Making sure that the app keeps running. We will use system d

sudo mv /tmp/application.service /etc/systemd/system/application.service

sudo systemctl enable application.service
sudo systemctl start application.service
sudo systemctl status application.service

#Install Docker + postgres
# Docker:
#curl -fsSL https://get.docker.com -o get-docker.sh
#sudo sh get-docker.sh
### 

#Docker hello:
#sudo docker run hello-world

#Postgres:
#sudo docker pull postgres
#sudo docker run --name postgresapp3 -e POSTGRES_USER=postgres     -e POSTGRES_PASSWORD=root1     -e POSTGRES_DB=application -p 5432:5432   -d postgres
#docker run --name postgresapp3 -e POSTGRES_USER=postgres     -e POSTGRES_DB=application  -e POSTGRES_HOST_AUTH_METHOD=trust -p 5432:5432   -d postgres
#sudo docker ps

## Adding the postgres server from bash using linux commands:
#sudo apt -y install postgresql postgresql-contrib
#sudo systemctl start postgresql.service
#sudo -u postgres createdb application
#sleep 30
#sudo -u postgres psql
#ALTER USER postgres PASSWORD 'myPassword';
#\q

#Write the service file for postgres container
#sudo mv /tmp/postgres.service /etc/systemd/system/postgres.service
#sudo systemctl enable postgres.service
#sudo systemctl start postgres.service
#sudo systemctl status postgres.service
