package com.techolution.hashmap.service.broker.client.connector

import groovy.util.logging.Slf4j
import org.springframework.cloud.cloudfoundry.CloudFoundryServiceInfoCreator
import org.springframework.cloud.cloudfoundry.Tags

/**
 *
 *
 * @author Gnanesh Arva
 * @since 24 Jul 2017 at 21:08
 */
@Slf4j
class HashMapServiceInfoCreator extends CloudFoundryServiceInfoCreator<HashMapServiceInfo> {

    HashMapServiceInfoCreator() {
        super(new Tags('HashBroker'))
        log.info "In HashMapServiceInfoCreator constructor"
    }

    @Override
    boolean accept(Map<String, Object> serviceData) {
        def credentials = serviceData['credentials']
        log.info "credentials : ${credentials} , class : ${credentials.getClass()}"
        credentials && credentials['uri'] && credentials['username'] && credentials['password']
    }

    @Override
    HashMapServiceInfo createServiceInfo(Map<String, Object> serviceData) {
        def credentials = serviceData['credentials']
        log.info "credentials : ${credentials} , class : ${credentials.getClass()}"
        new HashMapServiceInfo(credentials['name'], credentials['uri'], credentials['username'], credentials['password'])
    }
}
