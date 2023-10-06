echo "-----------------Update of repositories-----------------"
sudo apt update

ehco "---------firewall allows SSH connections----------"
sudo ufw allow OpenSSH

echo "-----------------Install apache2-----------------"
sudo apt install apache2

echo "-----------------Check status of apache2-----------------"
sudo systemctl status apache2

echo "-----------Adjusting firewall to allow apache http request"
sudo ufw allow 'Apache'

echo "-----------------Restart apache-----------------"
sudo systemctl restart apache

echo "--------Acitvate firewall--------"
sudo ufw enable
