package com.jad;

public class Factory {
    private static Factory instance;

    private Factory() {
    }

    public static Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public Foo createFoo(Bar bar) {
        return new Foo(bar);
    }

    public Bar createBar() {
        return new Bar();
    }

    public Baz createBaz() {
        return new Baz();
    }

    public Qux createQux() {
        return new Qux();
    }

    public Corge createCorge(Foo foo) {
        return new Corge(foo);
    }

    public Grault createGrault(Foo foo) {
        return new Grault(foo);
    }

    public FooBuilder createFooBuilder(Bar bar) {
        return new FooBuilder(bar);
    }
}
