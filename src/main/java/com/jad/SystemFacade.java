package com.jad;

import java.util.List;

/**
 * Facade pour simplifier l'accès au système.
 * Cette classe fournit une interface unifiée pour interagir avec les objets du domaine.
 */
public class SystemFacade {
    private static SystemFacade instance;
    private final Factory factory;

    private SystemFacade() {
        this.factory = Factory.getInstance();
    }

    public static SystemFacade getInstance() {
        if (instance == null) {
            instance = new SystemFacade();
        }
        return instance;
    }

    // Méthodes simplifiées pour l'accès au système

    public Foo createUnifiedSystem(Bar bar) {
        return factory.createFoo(bar);
    }

    public Foo createComplexSystem(Bar bar, Qux qux, List<Baz> bazs) {
        FooBuilder builder = factory.createFooBuilder(bar);
        if (qux != null) {
            builder.withQux(qux);
        }
        if (bazs != null) {
            for (Baz baz : bazs) {
                builder.addBaz(baz);
            }
        }
        return builder.build();
    }
}
