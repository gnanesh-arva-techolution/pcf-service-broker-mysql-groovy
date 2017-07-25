package com.techolution.hashmap.service.broker.service

import com.techolution.hashmap.service.broker.util.CustomHashMap
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 *
 *
 * @author Gnanesh Arva
 * @since 24 Jul 2017 at 18:42
 */
@Slf4j
@Service
class CustomHashMapService {

    @Autowired
    CustomHashMap<String, CustomHashMap<Object, Object>> customMap

    void put(String instanceId, String key, Object value) {
        customMap.get(instanceId).put(key, value)
    }

    Object get(String instanceId, String key) {
        customMap.get(instanceId).get(key)
    }

    void delete(String instanceId, String key) {
        customMap.get(instanceId).remove(key)
    }

    void delete(String instanceId) {
        customMap.remove(instanceId)
    }

    void create(String instanceId) {
        customMap.put(instanceId, new CustomHashMap<Object, Object>())
    }

}
