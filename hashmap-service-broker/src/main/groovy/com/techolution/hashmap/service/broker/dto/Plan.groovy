package com.techolution.hashmap.service.broker.dto

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
 * @since 24 Jul 2017 at 18:23
 */
@Entity
@Table(name = 'plans')
@ToString
@EqualsAndHashCode
class Plan {

    @Id
    String id
    @Column(nullable = false)
    String name
    @Column(nullable = false)
    String description

}
