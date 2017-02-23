package com.team7.model.visitor;

/**
 * visitor Pattern: Visitable Interface
 */
public interface Visitable {
    public void accept(Visitor visitor);
}
