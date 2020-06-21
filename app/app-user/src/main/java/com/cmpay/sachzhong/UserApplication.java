package com.cmpay.sachzhong;

import com.cmpay.lemon.common.LemonFramework;
import com.cmpay.lemon.framework.LemonBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;

@LemonBootApplication(exclude = {RabbitAutoConfiguration.class}, value = {"com.cmpay.sachzhong"})
public class UserApplication {
	public static void main(String[] args) {
		LemonFramework.run(UserApplication.class, args);
	}
}


