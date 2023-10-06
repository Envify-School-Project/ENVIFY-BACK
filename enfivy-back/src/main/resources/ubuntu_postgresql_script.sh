package="postgres"
version=15

echo "-----------------Add postgresql repo to apt list source-----------------"
sudo sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt $(lsb_release -cs)-pgdg main" > /etc/apt/sources.list.d/pgdg.list'

echo "-----------------Get postgresql source-----------------"
wget –quiet -O – https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add –

echo "-----------------Update repository-----------------"
sudo apt-get update

echo "-----------------Installation of postgresql-----------------"
sudo apt-get -y install postgresql-$version postgresql-contrib

echo "-----------------Start Postgres-----------------"
sudo dpkg –status postgresql

echo "-----------------Checking the status of Postgres-----------------"
sudo systemctl status postgresql.service
