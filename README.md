# Projet 2 de PROG1 : interpréteur MiniJava

Ce projet a pour but d'implémenter un interpréteur du langage MiniJava, une version simplifiée du langage Java. On peut diviser ce projet en trois grandes parties : l'interpréteur, qui calcule une valeur à partir d'un programme MiniJava, le vérificateur de types, qui s'assure de la correction d'une partie des opérations d'un programme, et les tests du bon fonctionnement de ces deux éléments.  
On discute donc ci-après des détails de l'implémentation de ces trois différentes parties.

## Gestion de l'interpréteur

truc(s) à dire :
- param > field en terme de priorité

## Gestion de la vérification de types

La vérification de type s'effectue de manière assez simple, avec des appels successifs sur les objets imbriqués de l'arbre syntaxique. On vérifie le type d'un programme en vérifiant que le type est correct pour toutes les déclarations de classes, et on vérifie que le type est correct pour toutes les déclarations de classes en vérifiant que toutes leurs variables et leurs déclarations de méthodes sont bien typées, etc.. On descend ainsi dans l'arbre syntaxique jusqu'aux expressions, où on évalue les types en fonction des variables (dont on a mémorisé au préalable la déclaration, et donc le type).

## Gestion des tests