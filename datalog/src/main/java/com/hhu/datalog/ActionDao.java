package com.hhu.datalog;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hhu.domain.Action;

/**
 * 将Action存到MongoDB中
 * @author Weiguo Liu
 *
 */
public interface ActionDao extends MongoRepository<Action,String> {

}
