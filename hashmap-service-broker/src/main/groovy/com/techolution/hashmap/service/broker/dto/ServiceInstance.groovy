package com.techolution.hashmap.service.broker.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 *
 *
 * @author Gnanesh Arva
 * @since 24 Jul 2017 at 18:26
 */
@Entity
@Table(name = 'service_instances')
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@ToString
@EqualsAndHashCode
class ServiceInstance {

    @Id
    String id
    @JsonSerialize
    @JsonProperty('service_id')
    @Column(nullable = false)
    String serviceId
    @JsonSerialize
    @JsonProperty('plan_id')
    @Column(nullable = false)
    String planId
    @JsonSerialize
    @JsonProperty('organization_guid')
    @Column(nullable = false)
    String organizationGuid
    @JsonSerialize
    @JsonProperty('space_guid')
    @Column(nullable = false)
    String spaceGuid


}
