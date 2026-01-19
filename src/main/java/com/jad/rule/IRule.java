package com.jad.rule;

import com.jad.Foo;

/**
 * Interface Strategy pour définir une règle métier.
 */
public interface IRule {
    int apply(Foo foo);
}
