echo "-----------------Update repository-----------------"
sudo apt-get update
echo "-----------------Add Java repository to download it-----------------"
sudo apt install libc6-i386 libc6-x32 libxi6 libxtst6 -y

echo "-----------------Download java-----------------"
wget https://download.oracle.com/java/$version/latest/jdk-$version_linux-x64_bin.deb

echo "-----------------Installation of Java-----------------"
sudo dpkg -i jdk-$version_linux-x64_bin.deb

echo "-----------------Checking the version of Java-----------------"
java -version
