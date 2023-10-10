package="postgres"
version=15

echo "-----------------Installation of Postgres-----------------"
sudo sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt $(lsb_release -cs)-pgdg main" > /etc/apt/sources.list.d/pgdg.list'
wget –quiet -O – https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add –

sudo apt-get update
sudo apt-get -y install postgresql-$version postgresql-contrib

echo "-----------------Start Postgres-----------------"
sudo dpkg –status postgresql

echo "-----------------Checking the status of Postgres-----------------"
sudo systemctl status postgresql.service
