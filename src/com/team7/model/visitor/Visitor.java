package com.team7.model.visitor;

import com.team7.model.resource.Resource;

/**
 * visitor pattern: visitor Interface
 */
public interface Visitor {
    public void visit(Resource resource);
}
