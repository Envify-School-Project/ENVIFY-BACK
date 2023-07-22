echo "-----------------Add mariadb repository to download it-----------------"
curl -LsS https://r.mariadb.com/downloads/mariadb_repo_setup | sudo bash

echo "-----------------Installation de mariadb-----------------"
sudo apt-get install mariadb-server mariadb-client -y

echo "-----------------Verification du status de mariadb-----------------"
sudo systemctl status mariadb


echo "-----------------Securisaiton de mysql-----------------"
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
