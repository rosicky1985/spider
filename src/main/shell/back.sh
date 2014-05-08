#back up script
#usage: added to cron tab to execute repeatlly.
#you can also call it with no parameter to run immediately.
#configuration
#$pwd where the spidered file stored
#install 
#you should make backup destination trust source via ssh.
pwd=/home/rosicky
now=`date "+%Y%m%d"`
user=rosicky
p=love
db=spider_junior
#script started below
mysqldump $db -u$user -p$p > $pwd/spider.bak.$now.sql

