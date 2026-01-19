package com.jad.rule;

import com.jad.Factory;
import com.jad.Foo;
import com.jad.Bar;
import com.jad.Baz;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RuleTest {

    @Test
    void testBazCountRule() {
        Factory factory = Factory.getInstance();
        Bar bar = factory.createBar();
        Foo foo = factory.createFoo(bar);
        foo.addBaz(factory.createBaz());
        foo.addBaz(factory.createBaz());

        IRule rule = new BazCountRule();
        assertEquals(2, rule.apply(foo), "BazCountRule should return the number of Bazs");
    }

    @Test
    void testBonusRuleDecorator() {
        Factory factory = Factory.getInstance();
        Bar bar = factory.createBar();
        Foo foo = factory.createFoo(bar);
        foo.addBaz(factory.createBaz());

        IRule rule = new BazCountRule();
        IRule bonusRule = new BonusRuleDecorator(rule, 10);

        // 1 Baz + 10 Bonus = 11
        assertEquals(11, bonusRule.apply(foo), "BonusRuleDecorator should add bonus to the result");
    }

    @Test
    void testDoubleRuleDecorator() {
        Factory factory = Factory.getInstance();
        Bar bar = factory.createBar();
        Foo foo = factory.createFoo(bar);
        foo.addBaz(factory.createBaz());
        foo.addBaz(factory.createBaz());

        IRule rule = new BazCountRule(); // 2
        IRule doubleRule = new DoubleRuleDecorator(rule); // 2 * 2 = 4

        assertEquals(4, doubleRule.apply(foo), "DoubleRuleDecorator should double the result");
    }

    @Test
    void testChainedDecorators() {
        Factory factory = Factory.getInstance();
        Bar bar = factory.createBar();
        Foo foo = factory.createFoo(bar);
        foo.addBaz(factory.createBaz()); // 1

        IRule rule = new BazCountRule();
        // (1 + 10) * 2 = 22
        IRule complexRule = new DoubleRuleDecorator(new BonusRuleDecorator(rule, 10));

        assertEquals(22, complexRule.apply(foo), "Decorators should be chainable");
    }

    @Test
    void testRuleEngine() {
        Factory factory = Factory.getInstance();
        Bar bar = factory.createBar();
        Foo foo = factory.createFoo(bar);
        foo.addBaz(factory.createBaz()); // Baz Count = 1
        foo.addGrault(); // Grault Count = 1

        RuleEngine engine = new RuleEngine();
        engine.addRule(new BazCountRule()); // +1
        engine.addRule(new GraultCountRule()); // +1
        
        // Total = 2
        assertEquals(2, engine.process(foo), "RuleEngine should sum up results of all rules");
    }
}
