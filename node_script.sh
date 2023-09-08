#!/bin/bash

# Afficher un message à l'utilisateur
package="NodeJs"
version="setup"+$1
# TODO variabilisé la versionde nodeJS avec un argument($) pour le script

echo "Welcome to the installation script of $package ! \n"
echo "----------This script installs NodeJs using the NodeSource PPA (personal package archive)-----------"

# lancer un update des packages
echo "-----------------Update of repositories-----------------"
sudo apt update -y

# Telechargement du script d'installation
echo "-----------------Download installation script----------------- \n"
echo "The path to the script is /tmp/nodesource_setup.sh \n"
curl -sL https://deb.nodesource.com/$version -o /tmp/nodesource_setup.sh

# Add NodeSource PPA to local package of ubuntu
echo "-----------------Add NodeSource PPA to local package of ubuntu-----------------"
sudo bash /tmp/nodesource_setup.sh

# Install nodeJS
echo "-----------------Installation of nodeJS-----------------"
sudo apt install nodejs

# Verify nodeJS version
echo "-----------------Checking the installed nodejs version-----------------"
sudo node -v

# Script for uninstall nodejs
echo " \n To uninstall nodejs you can type the following commands \n"
echo "  \n"

# Fin du script
echo "Fin du script."
echo "Installation réussir."
