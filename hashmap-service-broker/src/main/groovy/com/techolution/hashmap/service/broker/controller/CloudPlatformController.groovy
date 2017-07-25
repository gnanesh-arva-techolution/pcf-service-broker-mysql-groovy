package com.techolution.hashmap.service.broker.controller

import com.techolution.hashmap.service.broker.dto.Credentials
import com.techolution.hashmap.service.broker.dto.Service
import com.techolution.hashmap.service.broker.dto.ServiceBinding
import com.techolution.hashmap.service.broker.dto.ServiceInstance
import com.techolution.hashmap.service.broker.repository.ServiceBindingRepository
import com.techolution.hashmap.service.broker.repository.ServiceInstanceRepository
import com.techolution.hashmap.service.broker.repository.ServiceRepository
import com.techolution.hashmap.service.broker.service.CustomHashMapService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.Cloud
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import static org.springframework.http.HttpStatus.*

/**
 * This controller holds the end points to which cloud platform requests.
 * PCF requests for following:
 * 1) services-plans,
 * 2) to create service instance,
 * 3) to delete service instance,
 * 4) to bind service instance to an app,
 * 5) to unbind service instance from an app.
 * Total 5 routes (From PCF to Service Broker).
 *
 * @author Gnanesh Arva
 * @since 24 Jul 2017 at 18:53
 */
@RestController
@Slf4j
class CloudPlatformController {

    private static final String EMPTY_JSON = '{}'
    @Autowired
    ServiceRepository serviceRepository
    @Autowired
    ServiceInstanceRepository serviceInstanceRepository
    @Autowired
    ServiceBindingRepository serviceBindingRepository
    @Autowired
    Cloud cloud
    @Autowired
    CustomHashMapService customHashMapService

    @GetMapping('/v2/catalog')
    Map<String, Iterable<Service>> catalog() {
        Map<String, Iterable<Service>> map = [:]
        map['services'] = serviceRepository.findAll()
        map
    }

    @PutMapping('/v2/service_instances/{instanceId}')
    ResponseEntity<String> createServiceInstance(
            @PathVariable String instanceId, @RequestBody ServiceInstance serviceInstance) {
        log.info "In createServiceInstance. instanceId : ${instanceId}, serviceInstance : ${serviceInstance}"
        serviceInstance.id = instanceId
        ResponseEntity<String> responseEntity = null
        ServiceInstance existingServiceInstance = serviceInstanceRepository.findOne(instanceId)
        if (existingServiceInstance) {
            responseEntity = (existingServiceInstance == serviceInstance) ? new ResponseEntity<String>(EMPTY_JSON, OK) : new ResponseEntity<String>(EMPTY_JSON, CONFLICT)
        } else {
            serviceInstanceRepository.save(serviceInstance)
            customHashMapService.create(instanceId)
            log.info "Create new service instance with instance id : ${instanceId}"
            responseEntity = new ResponseEntity<String>(EMPTY_JSON, CREATED)
        }
        responseEntity
    }

    @DeleteMapping('/v2/service_instances/{instanceId}')
    ResponseEntity<String> deleteServiceInstance(
            @PathVariable String instanceId, @RequestParam('service_id') String serviceId,
            @RequestParam("plan_id") String planId) {
        ResponseEntity<String> responseEntity = null
        if (serviceInstanceRepository.exists(instanceId)) {
            serviceInstanceRepository.delete(instanceId)
            responseEntity = new ResponseEntity<>(EMPTY_JSON, OK)
        } else {
            responseEntity = new ResponseEntity<>(EMPTY_JSON, GONE)
        }
        responseEntity
    }

    @PutMapping('/v2/service_instances/{instanceId}/service_bindings/{bindingId}')
    ResponseEntity<Object> createBinding(@PathVariable String instanceId, @PathVariable String bindingId,
                                         @RequestBody ServiceBinding serviceBinding) {
        if (!serviceInstanceRepository.exists(instanceId)) {
            new ResponseEntity<String>("{\"description\":\"Service instance " + instanceId + " does not exist!\"", BAD_REQUEST)
        }
        ResponseEntity<Object> responseEntity = null
        serviceBinding.id = bindingId
        serviceBinding.instanceId = instanceId
        ServiceBinding existingServiceBinding = serviceBindingRepository.findOne(bindingId)
        if (existingServiceBinding) {
            responseEntity = (existingServiceBinding == serviceBinding) ? new ResponseEntity<Object>(['credentials': existingServiceBinding.credentials], OK) : new ResponseEntity<Object>(EMPTY_JSON, CONFLICT)
        } else {
            Credentials credentials = new Credentials(id: UUID.randomUUID().toString(), username: 'username', password: 'password')
            credentials.uri = cloud.applicationInstanceInfo.properties.get('uris').first().toString()
            serviceBinding.credentials = credentials
            serviceBindingRepository.save(serviceBinding)
            responseEntity = new ResponseEntity<Object>(['credentials': credentials], CREATED)
        }
        responseEntity
    }

    @DeleteMapping('/v2/service_instances/{instanceId}/service_bindings/{bindingId}')
    ResponseEntity<String> deleteBinding(@PathVariable String instanceId, @PathVariable String bindingId,
                                         @RequestParam('service_id') String serviceId,
                                         @RequestParam("plan_id") String planId) {
        ResponseEntity<String> responseEntity = null
        if (serviceBindingRepository.exists(bindingId)) {
            serviceBindingRepository.delete(bindingId)
            responseEntity = new ResponseEntity<>(EMPTY_JSON, OK)
        } else {
            responseEntity = new ResponseEntity<>(EMPTY_JSON, GONE)
        }
        responseEntity
    }

}
