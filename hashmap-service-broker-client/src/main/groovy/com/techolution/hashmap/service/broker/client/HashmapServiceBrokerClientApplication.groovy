package com.techolution.hashmap.service.broker.client

import com.techolution.hashmap.service.broker.client.connector.HashMapServiceInfo
import groovy.util.logging.Slf4j
import org.apache.http.auth.AuthScope
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.impl.client.BasicCredentialsProvider
import org.apache.http.impl.client.DefaultHttpClient
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.Cloud
import org.springframework.cloud.CloudFactory
import org.springframework.context.annotation.Bean
import org.springframework.http.client.ClientHttpRequestFactory
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@Slf4j
class HashmapServiceBrokerClientApplication {

    static void main(String[] args) {
        SpringApplication.run HashmapServiceBrokerClientApplication, args
    }

    @Bean
    Cloud createCloud() {
        new CloudFactory().cloud
    }

    @Bean
    HashMapServiceInfo hashMapServiceInfo() {
        HashMapServiceInfo hashMapServiceInfo
        createCloud().serviceInfos.each {
            hashMapServiceInfo = it instanceof HashMapServiceInfo ? it : null
        }
        log.info "config hashMapServiceInfo : ${hashMapServiceInfo}"
        if (!hashMapServiceInfo) {
            throw new RuntimeException('Unable to find hashmap service instance.')
        }
        hashMapServiceInfo
    }

    @Bean
    RestTemplate restTemplate() {
        DefaultHttpClient httpClient = new DefaultHttpClient()
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider()
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(hashMapServiceInfo().username, hashMapServiceInfo().password))
        httpClient.credentialsProvider = credentialsProvider
        ClientHttpRequestFactory reqFactory = new HttpComponentsClientHttpRequestFactory(httpClient)
        new RestTemplate(reqFactory)
    }

}