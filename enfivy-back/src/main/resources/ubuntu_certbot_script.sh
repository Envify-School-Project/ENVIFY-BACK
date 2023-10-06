echo "-----------------Update of repositories-----------------"
sudo apt update

ehco "---------firewall allows SSH connections----------"
sudo ufw allow OpenSSH

echo "-----------Remove old certbot before install new---------"
sudo apt remove certbot

echo "--------Install snap core-------"
sudo snap install core

echo "--------Refresh snap core-------"
sudo snap refresh core

echo "----------Install certbot-----------"
sudo snap install --classic certbot
