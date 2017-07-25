package com.techolution.hashmap.service.broker.dto

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.*

/**
 *
 *
 * @author Gnanesh Arva
 * @since 24 Jul 2017 at 18:22
 */
@Entity
@Table(name = 'services')
@ToString
@EqualsAndHashCode
class Service {

    @Id
    String id
    @Column(nullable = false)
    String name
    @Column(nullable = false)
    String description
    @Column(nullable = false)
    boolean bindable
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = 'service_id')
    Set<Plan> plans = []

}
