echo "-----------------Update of repositories-----------------"
sudo apt update -y

echo "-----------------Add mariadb repository to download it-----------------"
curl -LsS https://r.mariadb.com/downloads/mariadb_repo_setup | sudo bash

echo "-----------------Installation of mariadb-----------------"
sudo apt-get install mariadb-server mariadb-client -y

echo "-----------------Checking the status of mariadb-----------------"
sudo systemctl status mariadb


echo "-----------------Securing mysql-----------------"
sudo mysql_secure_installation
