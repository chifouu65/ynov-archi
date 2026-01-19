package com.jad;

/**
 * Adaptateur permettant d'intégrer ExternalSystem dans notre domaine.
 */
public class ExternalSystemAdapter implements ExternalSystem {
    private final Foo foo;

    public ExternalSystemAdapter(Foo foo) {
        this.foo = foo;
    }

    @Override
    public void performAction() {
        // Adapte l'appel externe vers une action interne du système
        // Ici, on pourrait imaginer que l'action externe correspond à l'ajout d'un Grault
        foo.addGrault();
    }
}
