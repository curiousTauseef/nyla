# NYLA Solutions Global
# Solutions Global	Overview

This Java API provides support for basic application wrappers for the use of FTP, HTTP, LOG4J logging,
groovy scripting, searching, Freemarker and more. 
There API(s) are add-ons to [Nyla Solutions Core] (https://github.com/nyla-solutions/nyla/tree/master/nyla.solutions.core).



#Log4J2 

Add the following to the config.properties

**Config properties**

	log4j.configurationFactory=nyla.solutions.global.operations.logging.Log4J2ConfigurationFactory
	log4j.patternLayout=%d{MM/dd/yyyy HH:mm:ss.SSS} %-5level  - %msg%n
	log4j.configurationFile=config.properties
	org.apache.logging.log4j.level=DEBUG
 

#Building

1. Change directory to the root directory
2. $gradle install

#FAQ

- I get the following /bin/sh: gpg: command not found
	
	1. Install the gpg https://www.gnupg.org/download
	2. /usr/local/gnupg-2.1/bin/gpg --gen-key
	
	Also see http://blog.sonatype.com/2010/01/how-to-generate-pgp-signatures-with-maven/#.Vu84SRIrKjQ
	