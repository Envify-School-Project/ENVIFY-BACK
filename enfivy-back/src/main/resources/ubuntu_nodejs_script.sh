echo "-----------------Update of repositories-----------------"
sudo apt update -y

echo "-----------------Download installation script-----------------"
curl -sL https://deb.nodesource.com/setup$version -o /tmp/nodesource_setup.sh

echo "-----------------Add NodeSource PPA to local package of ubuntu-----------------"
sudo bash /tmp/nodesource_setup.sh

echo "-----------------Installation of nodeJS-----------------"
sudo apt install nodejs

echo "-----------------Checking the installed nodejs version-----------------"
sudo node -v
