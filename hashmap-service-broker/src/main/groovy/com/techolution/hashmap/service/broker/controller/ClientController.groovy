package com.techolution.hashmap.service.broker.controller

import com.techolution.hashmap.service.broker.service.CustomHashMapService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import static org.springframework.http.HttpStatus.*

/**
 *
 *
 * @author Gnanesh Arva
 * @since 24 Jul 2017 at 19:44
 */
@RestController
@Slf4j
class ClientController {

    private static final String EMPTY_JSON = '{}'

    @Autowired
    CustomHashMapService customHashMapService

    @PutMapping('/hash/{instanceId}/{key}')
    ResponseEntity<String> put(@PathVariable String instanceId, @PathVariable String key, @RequestBody String value) {
        customHashMapService.put(instanceId, key, value)
        new ResponseEntity<String>(EMPTY_JSON, CREATED)
    }

    @GetMapping('/hash/{instanceId}/{key}')
    ResponseEntity<Object> get(@PathVariable String instanceId, @PathVariable String key) {
        def value = customHashMapService.get(instanceId, key)
        log.info "Got value for key : ${key}, value : ${value}, instanceId : ${instanceId}"
        value ? new ResponseEntity<>(value, OK) : new ResponseEntity<>(EMPTY_JSON, NOT_FOUND)
    }

}
