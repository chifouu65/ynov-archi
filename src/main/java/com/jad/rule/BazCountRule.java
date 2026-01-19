package com.jad.rule;

import com.jad.Foo;

/**
 * Stratégie concrète : Compte le nombre de Baz.
 */
public class BazCountRule implements IRule {
    @Override
    public int apply(Foo foo) {
        if (foo == null || foo.getBazs() == null) {
            return 0;
        }
        return foo.getBazs().size();
    }
}
