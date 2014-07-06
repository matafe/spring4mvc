package com.matafe.springmvc.core.util;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * Define a service only for queries.
 * 
 * @author Mauricio T. Ferraz
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public abstract class AbstractQueryService {

}
