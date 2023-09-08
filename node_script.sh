#!/bin/bash

# Beginning of the script
package="NodeJs"
version="setup"+$1

echo "Welcome to the installation script of $package ! \n"
echo "----------This script installs NodeJs using the NodeSource PPA (personal package archive)-----------"

echo "-----------------Update of repositories-----------------"
sudo apt update -y

echo "-----------------Download installation script----------------- \n"
echo "The path to the script is /tmp/nodesource_setup.sh \n"
curl -sL https://deb.nodesource.com/$version -o /tmp/nodesource_setup.sh

echo "-----------------Add NodeSource PPA to local package of ubuntu-----------------"
sudo bash /tmp/nodesource_setup.sh

echo "-----------------Installation of nodeJS-----------------"
sudo apt install nodejs

echo "-----------------Checking the installed nodejs version-----------------"
sudo node -v

echo " \n To uninstall nodejs you can type the following commands \n"
echo "  \n"

# Fin du script
echo "Fin du script."
echo "Installation réussir."
