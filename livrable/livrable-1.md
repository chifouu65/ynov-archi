# PROJET FIL ROUGE - ARCHITECTURE LOGICIELLE
## Plateforme SaaS de Gestion de Services B2B

**Livrable Séance 1 - Analyse & Découpage Métier :** 

---

## 1. Contexte Global & Objectifs
Ce projet vise à concevoir et développer le socle technique d'une solution SaaS B2B destinée à un éditeur de logiciels. La plateforme centralise la relation client autour de quatre axes majeurs : la gestion des comptes, le support technique (ticketing), la facturation des services et le pilotage de l'activité.

### Enjeux Architecturaux
* **Modularité :** Capacité à faire évoluer un bloc métier sans impacter les autres.
* **Maintenabilité :** Code clair, découplé et testable pour supporter une croissance rapide.
* **Abstraction :** Indépendance vis-à-vis de la base de données (Persistence Ignorance).

---

## 2. Analyse Fonctionnelle Simplifiée

### 2.1. Acteurs du Système (Personas)
Le système doit répondre aux besoins de deux catégories d'utilisateurs :

**A. Utilisateurs Internes (L'éditeur)**
* **Administrateur Système :** Configuration globale, gestion des offres et des rôles.
* **Agent de Support :** Traitement des tickets, saisie des temps d'intervention.
* **Manager :** Consultation des tableaux de bord (Reporting), suivi de la facturation.

**B. Utilisateurs Externes (Les Clients B2B)**
* **Admin Client :** Gestion de ses propres collaborateurs, accès aux factures, souscription.
* **Utilisateur Final :** Création de tickets d'incidents ou de demandes, suivi de l'avancement.

### 2.2. Périmètre Fonctionnel
La plateforme doit permettre de :
1.  Gérer l'authentification et les droits d'accès granulaires (RBAC).
2.  Traiter le cycle de vie complet d'une demande client (Ticket).
3.  Calculer automatiquement les factures basées sur les abonnements et les interventions.
4.  Fournir une vue synthétique de l'activité via des indicateurs clés (KPIs).

---

## 3. Découpage en Blocs Métiers (Architecture Modulaire)

Pour respecter le principe de **Séparation des Préoccupations (SoC)**, le système est découpé en 5 modules autonomes.

### Module A : Gestion des Utilisateurs (Identity & Access)
*Responsable de la sécurité et de l'annuaire.*
* **Fonctionnalités :** Login, Logout, CRUD Utilisateurs, Gestion des Rôles & Permissions.
* **Entités Clés :** `User`, `Role`, `Permission`, `Company`.
* **Dépendances :** Aucune (Module de base).

### Module B : Gestion des Tickets (Support)
*Cœur opérationnel du service client.*
* **Fonctionnalités :** Création de ticket, Assignation agent, Changement de statut, Commentaires.
* **Entités Clés :** `Ticket`, `Status`, `Category`, `Comment`, `Priority`.
* **Dépendances :** Utilise *Module Utilisateurs* (pour l'auteur et l'assigné).

### Module C : Facturation (Billing)
*Gestion économique et contractuelle.*
* **Fonctionnalités :** Gestion des offres (Plans), Abonnements clients, Génération de factures mensuelles.
* **Entités Clés :** `Subscription`, `Plan`, `Invoice`, `InvoiceLine`.
* **Dépendances :** Utilise *Module Utilisateurs* (pour l'entreprise cliente).

### Module D : Reporting (Analytics)
*Outil d'aide à la décision.*
* **Fonctionnalités :** Calcul des KPIs (Temps de réponse, CA mensuel), Tableaux de bord.
* **Entités Clés :** `Dashboard`, `Metric`, `Report`.
* **Dépendances :** Utilise *Module Tickets* et *Module Facturation* (en lecture seule pour agrégation).

### Module E : Noyau Commun (Shared Kernel)
*Bibliothèque technique transverse.*
* **Contenu :** Classes utilitaires, Exceptions personnalisées, Interfaces génériques (DTOs de base).
* **Règle :** Ne contient **aucune** règle métier spécifique. Tous les modules dépendent de lui.

---

## 4. Matrice des Interactions (Dépendances Logiques)

| Module Source | Dépend de | Raison de la dépendance |
| :--- | :--- | :--- |
| **Tickets** | Utilisateurs | A besoin d'identifier l'auteur (`userId`) et l'agent (`agentId`). |
| **Facturation** | Utilisateurs | A besoin des infos légales de l'entreprise cliente (`Company`). |
| **Reporting** | Tickets, Facturation | A besoin des données brutes pour calculer les statistiques. |
| **Tous** | Noyau Commun | Utilisation des exceptions et utilitaires. |

---

## 5. Objectifs de Qualité Logicielle (KPIs Projet)

Ces indicateurs seront vérifiés à chaque étape du projet :

1.  **Faible Couplage :**
    * *Objectif :* Aucun cycle de dépendance entre les modules Maven.
    * *Vérification :* `mvn dependency:tree` propre.

2.  **Cohésion Forte :**
    * *Objectif :* Une classe ne gère qu'une seule responsabilité (SRP).
    * *Exemple :* Une classe `TicketService` ne doit pas générer de PDF de facture.

3.  **Testabilité :**
    * *Objectif :* Isolation du métier grâce aux Interfaces (Séance 5).
    * *Métrique :* Capacité à tester le service métier sans base de données active (mock).
