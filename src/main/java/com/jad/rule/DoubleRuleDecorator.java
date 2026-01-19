package com.jad.rule;

import com.jad.Foo;

/**
 * Decorator concret : Double le résultat de la règle.
 */
public class DoubleRuleDecorator extends RuleDecorator {

    public DoubleRuleDecorator(IRule rule) {
        super(rule);
    }

    @Override
    public int apply(Foo foo) {
        return super.apply(foo) * 2;
    }
}
