package com.techolution.hashmap.service.broker

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.Cloud
import org.springframework.cloud.CloudFactory
import org.springframework.context.annotation.Bean

/**
 *
 *
 * @author Gnanesh Arva
 * @since 24 Jul 2017 at 18:15
 */
@SpringBootApplication
class HashmapServiceBrokerApplication {

    static void main(String[] args) {
        SpringApplication.run HashmapServiceBrokerApplication, args
    }

    @Bean
    Cloud createCloud() {
        new CloudFactory().cloud
    }

}
