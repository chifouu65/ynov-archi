package com.jad.rule;

import com.jad.Foo;

/**
 * Decorator concret : Ajoute un bonus fixe au résultat de la règle.
 */
public class BonusRuleDecorator extends RuleDecorator {
    private final int bonus;

    public BonusRuleDecorator(IRule rule, int bonus) {
        super(rule);
        this.bonus = bonus;
    }

    @Override
    public int apply(Foo foo) {
        return super.apply(foo) + bonus;
    }
}
