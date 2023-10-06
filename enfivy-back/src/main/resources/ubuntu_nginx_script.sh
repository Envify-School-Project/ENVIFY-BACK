echo "-----------------Update of repositories-----------------"
sudo apt update

ehco "---------firewall allows SSH connections----------"
sudo ufw allow OpenSSH

echo "-----------------Install nginx-----------------"
sudo apt install nginx

echo "-----------Adjusting firewall to allow nginx http request"
sudo ufw allow 'Nginx HTTP'

echo "-----------------Check status of nginx-----------------"
sudo systemctl status nginx


echo "-----------------Restart nginx-----------------"
sudo systemctl restart nginx

echo "--------Acitvate firewall--------"
sudo ufw enable
