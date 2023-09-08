#!/bin/bash

# Afficher un message à l'utilisateur
package="MariaDb"

echo "Welcome to the installation script of $package ! \n"
echo "----------This script is valid on the Linux distribution Ubuntu 22.04-----------"

# lancer un update des packages
echo "-----------------Update of repositories-----------------"
sudo apt update -y

echo "-----------------Add mariadb repository to download it-----------------"
curl -LsS https://r.mariadb.com/downloads/mariadb_repo_setup | sudo bash

echo "-----------------Installation of mariadb-----------------"
sudo apt-get install mariadb-server mariadb-client -y

echo "-----------------Checking the status of mariadb-----------------"
sudo systemctl status mariadb | grep mariadb

echo "-----------------Securing mysql-----------------"
echo "Reply to the prompt with the answers below to secure your database \n"
echo "Enter current password for root (enter for none): Press enter as there is no password by default. \n
      Set root password? [Y/n]: Select Y and enter a new password. \n
      Remove anonymous users? [Y/n]: Select Y \n
      Disallow root login remotely? [Y/n]: Enter Y \n
      Remove the test database and access to it? [Y/n]: Enter Y \n
      Reload privilege tables now? [Y/n]: Enter Y \n "
#Enter current password for root (enter for none): Press enter as there is no password by default.
#Set root password? [Y/n]: Select Y and enter a new password.
#Remove anonymous users? [Y/n]: Select Y
#Disallow root login remotely? [Y/n]: Enter Y
#Remove the test database and access to it? [Y/n]: Enter Y
#Reload privilege tables now? [Y/n]: Enter Y
sudo mysql_secure_installation

# Fin du script
echo "End of script."
echo "Installation succeed."
