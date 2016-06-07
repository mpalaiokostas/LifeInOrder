# Project installation #


## Deploy a tomcat instance ##

1. Go to /usr/local/apache-tomcat-8.0.35/conf/tomcat-users.xml
2. Add user with preferred access rights as the following example
> \<role rolename="manager-gui"/\>  
> \<role rolename="admin-gui"/\>  
> \<user username="admin" password="admin" roles="manager-gui,admin-gui"/\>  

3. Go to /usr/local/apache-tomcat-8.0.35/bin
4. Run Tomcat server by typing sh startup.sh

## Setup war on tomcat webapps ##  

1. Copy the WAR file into the **Tomcat/webapps/** directory.  
2. Give permissions to user and group as following  
>  chown -R **user:group** /usr/local/apache-tomcat-8.0.35/  
3. Configure JNDI Resource in Tomcat  
> \<Resource  
>   name="jdbc/**name_of_database**"  
>   auth="Container"  
>   type="javax.sql.DataSource"  
>   maxActive="100"  
>   maxIdle="30"  
>   maxWait="10000"  
>   driverClassName="com.mysql.jdbc.Driver"  
>   url="jdbc:mysql://localhost:3306/usersDB"  
>   username="root"  
>   password="secret"  
>   />  
4. Open the web browser and navigate to url **http://localhost:8080/name_of_war/** 

## Troubleshooting ##

Check the contents of the war file by typing on your console
> jar tvf webApp-1.0.war

the structure must be something like the following
```
     0 Sun Jun 05 12:12:06 BST 2016 META-INF/
   284 Sun Jun 05 12:12:06 BST 2016 META-INF/MANIFEST.MF
     0 Sun Jun 05 12:12:04 BST 2016 icons/
     0 Sun Jun 05 12:12:04 BST 2016 scripts/
     0 Sun Jun 05 12:12:04 BST 2016 scripts/config/
     0 Sun Jun 05 12:12:04 BST 2016 scripts/controllers/
     0 Sun Jun 05 12:12:04 BST 2016 styles/
     0 Sun Jun 05 12:12:04 BST 2016 views/
     0 Sun Jun 05 12:12:04 BST 2016 WEB-INF/
     0 Sun Jun 05 12:12:04 BST 2016 WEB-INF/classes/
     0 Sun Jun 05 12:12:04 BST 2016 WEB-INF/classes/life/
     0 Sun Jun 05 12:12:04 BST 2016 WEB-INF/classes/life/database/
     0 Sun Jun 05 12:12:04 BST 2016 WEB-INF/classes/life/database/dao/
     0 Sun Jun 05 12:12:06 BST 2016 WEB-INF/lib/
  1150 Mon Mar 28 21:30:22 BST 2016 icons/favicon.ico
  4124 Wed May 04 23:34:52 BST 2016 index.jsp
```
