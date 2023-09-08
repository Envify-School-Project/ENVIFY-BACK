#!/bin/bash

# Afficher un message à l'utilisateur
package="maariadb"

echo "Welcome to the installation script of $package !"
echo "----------This script is valid on the Linux distribution Ubuntu-----------"

# lancer un update des packages
echo "-----------------Update of repositories-----------------"
sudo apt update -y
