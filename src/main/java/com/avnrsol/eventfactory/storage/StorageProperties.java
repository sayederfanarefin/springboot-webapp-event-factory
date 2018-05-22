package com.avnrsol.eventfactory.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

	
	  	   private String location = "/opt/tomcat/webapps/abc"; //for droplet

    
//private String location = "upload-dir"; //for mac

    
    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
