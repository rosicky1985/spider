#back up script
#usage: added to cron tab to execute repeatlly.
#you can also call it with no parameter to run immediately.
#configuration
#$pwd where the spidered file stored
#install 
#you should make backup destination trust source via ssh.
pwd=/opt/apache-tomcat-6.0.32/webapps/spider/resources/
echo "pwd is $pwd"

