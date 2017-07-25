package com.techolution.hashmap.service.broker.repository

import com.techolution.hashmap.service.broker.dto.Service
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 *
 *
 * @author Gnanesh Arva
 * @since 24 Jul 2017 at 18:34
 */
@Repository
interface ServiceRepository extends CrudRepository<Service, String> {

}