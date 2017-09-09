
PostIt is a spring boot web based mail client.

It uses a user database loaded in Apache Geode/GemFire.

# Startup

Start the Geode "gfsh" tool

	gfsh

First a Geode locator and cache server using Gfsh


	gfsh>start locator --name=local --enable-cluster-configuration
	gfsh>start server --name=server1 --use-cluster-configuration

Create the region that will contain save emails address/contact information in gfsh

	gfsh>create region --name=UserProfile --type=PARTITION
	
Configuration Geode PDX to be read serialized
	
	configure pdx --read-serialized=true
	
Restart the	cache server

	gfsh>stop server --name=server1
	gfsh>start server --name=server1 --use-cluster-configuration

	
Start Spring Boot App

	java -jar nyla.solutions.net.postit-VERSION.jar
	
Access URL http://localhost:8080