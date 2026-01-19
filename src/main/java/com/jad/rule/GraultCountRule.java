package com.jad.rule;

import com.jad.Foo;

/**
 * Stratégie concrète : Compte le nombre de Grault.
 */
public class GraultCountRule implements IRule {
    @Override
    public int apply(Foo foo) {
        if (foo == null || foo.getGraults() == null) {
            return 0;
        }
        return foo.getGraults().size();
    }
}
