package com.jad.rule;

import com.jad.Foo;

/**
 * Decorator abstrait pour enrichir les règles.
 */
public abstract class RuleDecorator implements IRule {
    protected final IRule decoratedRule;

    public RuleDecorator(IRule rule) {
        this.decoratedRule = rule;
    }

    @Override
    public int apply(Foo foo) {
        return decoratedRule.apply(foo);
    }
}
