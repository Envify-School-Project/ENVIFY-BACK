#!/bin/bash

# Afficher un message à l'utilisateur
package="NodeJs"
version="setup"+$1
# TODO variabilisé la versionde nodeJS avec un argument($) pour le script

echo "Bienvenue dans le script d'intall de $package ! \n"
echo "----------Ce script install NodeJs en utilisant le NodeSource PPA (personal package archive)-----------"

# lancer un update des packages
echo "-----------------Mise à jour des repository-----------------"
sudo apt update -y

# Telechargement du script d'installation
echo "-----------------Telechargement du script d'installation----------------- \n"
echo "Le chemin vers le script est /tmp/nodesource_setup.sh \n"
curl -sL https://deb.nodesource.com/$version -o /tmp/nodesource_setup.sh

# Add NodeSource PPA to local package of ubuntu
echo "-----------------Add NodeSource PPA to local package of ubuntu-----------------"
sudo bash /tmp/nodesource_setup.sh

# Install nodeJS
echo "-----------------Installation de nodeJS-----------------"
sudo apt install nodejs

# Verify nodeJS version
echo "-----------------Vérification de la version de nodejs installé-----------------"
sudo node -v

# Script for uninstall nodejs
echo " \n Pour desinstaller nodejs vous pouvez taper les commandes suivantes \n"
echo "  \n"

# Fin du script
echo "Fin du script."
echo "Installation réussir."
