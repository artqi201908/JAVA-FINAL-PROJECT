### FWRP 
Food Waste Reduction Platform

## environment
```aidl
java 17
maven-3.8.5
tomcat-9.0.87
mysql-5.7.31-winx64
sql script: fwrp-MySQL.sql
```

## start up
```aidl
mvn clean install
config tomcat in IDEA and add artifact
run tomcat
```

## accounts
```aidl
Retailer: retailer1/retailer1
Charitable Organization: charity1/charity1
Consumer: consumer1/consumer1
```

## Email setup
```aidl
Google 
Manager your Account
Security
enable 2FA - 2-steps verification
go to 2FA and go to the bottom - App passwords
create app password
use the app password in code like this, ex
final String username = "xx@gmail.com";
final String password = "eatr hcdu hudt dwdx"; 
```
