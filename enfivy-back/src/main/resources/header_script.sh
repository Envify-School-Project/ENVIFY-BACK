#!/bin/bash

# Afficher un message à l'utilisateur
package="maariadb"

echo "Bienvenue dans le script d'intall de $package !"

# lancer un update des packages
echo "-----------------Mise à jour des repository-----------------"
sudo apt update -y