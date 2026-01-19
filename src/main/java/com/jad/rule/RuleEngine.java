package com.jad.rule;

import com.jad.Foo;

import java.util.ArrayList;
import java.util.List;

/**
 * Moteur de règles : Exécute une ou plusieurs règles sur un Foo.
 */
public class RuleEngine {
    private List<IRule> rules = new ArrayList<>();

    public void addRule(IRule rule) {
        rules.add(rule);
    }

    public int process(Foo foo) {
        int totalScore = 0;
        for (IRule rule : rules) {
            totalScore += rule.apply(foo);
        }
        return totalScore;
    }
}
