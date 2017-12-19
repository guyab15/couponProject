package spring.storage;

import org.springframework.context.annotation.Configuration;

@Configuration //Properties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "/Users/guyAvraham/Desktop/image/";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
