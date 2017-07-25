package com.techolution.hashmap.service.broker.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.*

/**
 *
 *
 * @author Gnanesh Arva
 * @since 24 Jul 2017 at 18:30
 */
@Entity
@Table(name = 'service_bindings')
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@ToString
@EqualsAndHashCode
class ServiceBinding {

    @Id
    String id
    @Column(nullable = false)
    String instanceId
    @JsonSerialize
    @JsonProperty('service_id')
    @Column(nullable = false)
    String serviceId
    @JsonSerialize
    @JsonProperty('plan_id')
    @Column(nullable = false)
    String planId
    @JsonSerialize
    @JsonProperty('app_guid')
    @Column(nullable = false)
    String appGuid
    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = 'service_binding_id')
    Credentials credentials
}
