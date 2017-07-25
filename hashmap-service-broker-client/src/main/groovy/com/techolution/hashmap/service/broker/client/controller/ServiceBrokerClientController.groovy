package com.techolution.hashmap.service.broker.client.controller

import com.techolution.hashmap.service.broker.client.connector.HashMapServiceInfo
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate

/**
 * You, the developer should hit these end points from browser or command line to see the response.
 *
 * @author Gnanesh Arva
 * @since 24 Jul 2017 at 21:22
 */
@RestController
@Slf4j
class ServiceBrokerClientController {

    @Autowired
    HashMapServiceInfo hashMapServiceInfo
    @Autowired
    RestTemplate restTemplate

    @GetMapping('/serviceInfo')
    HashMapServiceInfo hashMapServiceInfo() {
        log.info "info hashMapServiceInfo : ${hashMapServiceInfo}"
        hashMapServiceInfo
    }

    @PutMapping('/{key}')
    ResponseEntity<String> put(@PathVariable String key, @RequestBody String value) {
        log.info "put hashMapServiceInfo : ${hashMapServiceInfo}"
        restTemplate.put(hashMapServiceInfo.uri + '/{key}', value, key)
        new ResponseEntity<>('{}', HttpStatus.CREATED)
    }

    @GetMapping('/{key}')
    ResponseEntity<String> get(@PathVariable String key) {
        log.info "get hashMapServiceInfo : ${hashMapServiceInfo}"
        def value = restTemplate.getForObject(hashMapServiceInfo.uri + '/{key}', String.class, key)
        new ResponseEntity<>(value, HttpStatus.OK)
    }
}
