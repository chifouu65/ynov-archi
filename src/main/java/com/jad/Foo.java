package com.jad;

import java.util.ArrayList;
import java.util.List;
public class Foo {
    private Bar bar;
    private List<Baz> bazs = new ArrayList<>();
    private Qux qux;
    private Corge corge;
    private List<Grault> graults = new ArrayList<>();

    // Constructeur
    public Foo(Bar bar) {
        this.bar = bar;
        this.qux = new Qux(); // Initialise Qux par défaut
    }

    // Getters et Setters
    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public List<Baz> getBazs() {
        return bazs;
    }

    public void setBazs(List<Baz> bazs) {
        this.bazs = bazs;
    }

    public Qux getQux() {
        return qux;
    }

    public void setQux(Qux qux) {
        this.qux = qux;
    }

    public Corge getCorge() {
        return corge;
    }

    public void setCorge(Corge newCorge) {
        if (this.corge == newCorge) {
            return; // Empêche les appels redondants
        }
        // Supprime la référence à l'ancien Corge
        if (this.corge != null) {
            Corge oldCorge = this.corge;
            this.corge = null; // Évite les appels redondants
            oldCorge.setFoo(null);
        }
        // Met à jour la référence au nouveau Corge
        this.corge = newCorge;
        if (newCorge != null && newCorge.getFoo() != this) {
            newCorge.setFoo(this);
        }
    }

    public List<Grault> getGraults() {
        return graults;
    }

    public void setGraults(List<Grault> graults) {
        this.graults = graults;
    }

    public void addBaz(Baz baz) {
        this.bazs.add(baz);
    }

    public void addGrault() {
        Grault grault = new Grault(this);
        this.graults.add(grault);
    }
}
