package com.techolution.hashmap.service.broker.client.connector

import groovy.transform.ToString
import org.springframework.cloud.service.BaseServiceInfo
import org.springframework.cloud.service.ServiceInfo

/**
 *
 *
 * @author Gnanesh Arva
 * @since 24 Jul 2017 at 21:06
 */
@ToString
class HashMapServiceInfo extends BaseServiceInfo {

    String uri
    String username
    String password

    HashMapServiceInfo(String id, String uri, String username, String password) {
        super(id)
        this.uri = uri
        this.username = username
        this.password = password
    }

    @ServiceInfo.ServiceProperty
    String getUri() {
        return uri
    }

    @ServiceInfo.ServiceProperty
    String getUsername() {
        return username
    }

    @ServiceInfo.ServiceProperty
    String getPassword() {
        return password
    }
}
