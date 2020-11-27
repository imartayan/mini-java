# Interpréteur MiniJava

Ce projet a pour but d'implémenter un interpréteur du langage MiniJava, une version simplifiée du langage Java.
On peut diviser ce projet en trois grandes parties :
* l'interpréteur, qui calcule une valeur à partir d'un programme MiniJava
* le vérificateur de types, qui s'assure de la correction d'une partie des opérations d'un programme
* les tests du bon fonctionnement de ces deux éléments

On discute donc ci-après des détails de l'implémentation de ces trois différentes parties.

## Gestion de l'interpréteur

truc(s) à dire :
- param > field en terme de priorité

## Gestion de la vérification de types

La vérification de type s'effectue de manière assez simple, avec des appels successifs sur les objets imbriqués de l'arbre syntaxique.
On vérifie ainsi que le typage d'un programme est correct en vérifiant le typage de la classe principale (contenant main) et de toutes les déclarations de classes qui suivent.
La vérification des déclarations de classes se fait en vérifiant le typage des variables et et des méthodes, et ainsi de suite.
De cette façon, on descend dans l'arbre syntaxique jusqu'aux expressions, où on évalue les types en fonction des variables (dont on a mémorisé au préalable la déclaration, et donc le type).

## Gestion des tests