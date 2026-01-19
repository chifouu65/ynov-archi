package com.jad;

import java.util.ArrayList;
import java.util.List;

public class FooBuilder {
    private Bar bar;
    private List<Baz> bazs = new ArrayList<>();
    private Qux qux;
    private Corge corge;
    private List<Grault> graults = new ArrayList<>();

    public FooBuilder(Bar bar) {
        this.bar = bar;
    }

    public FooBuilder addBaz(Baz baz) {
        this.bazs.add(baz);
        return this;
    }

    public FooBuilder withQux(Qux qux) {
        this.qux = qux;
        return this;
    }

    public FooBuilder withCorge(Corge corge) {
        this.corge = corge;
        return this;
    }

    public FooBuilder addGrault(Grault grault) {
        this.graults.add(grault);
        return this;
    }

    public Foo build() {
        Foo foo = new Foo(bar);
        if (qux != null) {
            foo.setQux(qux);
        }
        if (corge != null) {
            foo.setCorge(corge);
        }
        for (Baz baz : bazs) {
            foo.addBaz(baz);
        }

        return foo;
    }
}
