package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserInfo {
	
	private static final Logger log = LoggerFactory.getLogger(UserInfo.class);

    private final String name;
    private final Integer age;

    public UserInfo(String name, Integer age) {
    	log.info("Creating user " + name + ". age: " + age);
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}