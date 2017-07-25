package com.techolution.hashmap.service.broker.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 *
 *
 * @author Gnanesh Arva
 * @since 24 Jul 2017 at 18:20
 */
@Entity
@Table(name = 'credentials')
@JsonIgnoreProperties(['id'])
class Credentials {

    @Id
    String id
    @Column(nullable = false)
    String uri
    @Column(nullable = false)
    String username
    @Column(nullable = false)
    String password

}
