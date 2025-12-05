package com.jad;
public class Corge {
    private Foo foo;

    // Constructeur
    public Corge(Foo foo) {
        this.foo = null; // Initialise à null pour éviter les boucles immédiates
        if (foo != null) {
            foo.setCorge(this); // Met à jour la relation bidirectionnelle
        }
    }

    // Getter et Setter pour Foo
    public Foo getFoo() {
        return foo;
    }

    public void setFoo(Foo newFoo) {
        if (this.foo == newFoo) {
            return; // Empêche les appels redondants
        }
        // Supprime la référence à l'ancien Foo
        if (this.foo != null) {
            Foo oldFoo = this.foo;
            this.foo = null; // Évite les appels redondants
            oldFoo.setCorge(null);
        }
        // Met à jour la référence au nouveau Foo
        this.foo = newFoo;
        if (newFoo != null && newFoo.getCorge() != this) {
            newFoo.setCorge(this);
        }
    }
}
