# Interpréteur MiniJava

Ce projet a pour but d'implémenter un interpréteur du langage MiniJava, une version simplifiée du langage Java.
On peut diviser ce projet en trois grandes parties :
* l'interpréteur, qui calcule une valeur à partir d'un programme MiniJava
* le vérificateur de types, qui s'assure de la correction d'une partie des opérations d'un programme
* les tests du bon fonctionnement de ces deux éléments

On discute donc ci-après des détails de l'implémentation de ces trois différentes parties.

## Gestion de l'interpréteur

Interpreter a reçu de nouveaux champs : arrays et objects. Ils font le lien entre les noms de variables de type Identifier et l'objet ou le tableau qu'ils représentent. La possibilité de mettre ces champs dans Heap ou dans SimpleHeap a été étudiée mais n'a pas été retenue : dans Heap, il fallait leur donner une valeur par défaut, ce qui ne correspondait pas à ce que nous attendions. Dans SimpleHeap, nous pouvions laisser les champs sans initialisation, mais comme ces champs n'appartenaient pas à la classe mère, il nous fallait caster tous les Heap en SimpleHeap pour pouvoir les utiliser, ce qui n'était pas pratique, surtout dans l'optique d'utiliser ensuite AvancedHeap.
Interpreter a également reçu un champ currentObject, qui contient l'identifiant de l'objet sur le-quel l'interpreteur execute des choses actuellement. Ce champs est notament utile pour le fichier ThisExpression, où lors d'appel de méthodes.
On a recontré la nécéssité d'avoir pour tous les objets crés, que leurs adresses soient stockés dans une variable ou non, un identifiant pour pouvoir s'y référer autrement que par leur valeur-adresse. Nous avons donc créer une nouvelle classe UniqueIdentifier, qui hérite de Identifier et qui, à chaque fois qu'elle est appellée renvoit un identifiant unique. Cet identifiant ne peut pas entrer en collision avec les noms de variable que donne l'utilisateur.
Concernant la visibilité de certaines variables, puisque l'on ne possède pas d'instruction type A.x permettant d'accéder au champs d'une instance de classe, on a fait le choix que si une variable est définie globalement dans une classe et localement dans une méthode de cette classe, la variable visible était celle définie le plus proche donc la variable locale de la méthode.

Nous avons manqué de temps pour la correction des problèmes des méthodes eval. L'eval de MessageSend semble poser un problème que nous n'avons pas réussi à préciser et à résoudre. Cela empêche un grand nombre de tests de réussir.

## Gestion de la vérification de types

La vérification de type s'effectue de manière assez simple, avec des appels successifs sur les objets imbriqués de l'arbre syntaxique.
On vérifie ainsi que le typage d'un programme est correct en vérifiant le typage de la classe principale (contenant main) et de toutes les déclarations de classes qui suivent.

La vérification des déclarations de classes se fait en vérifiant le typage des variables et et des méthodes, et ainsi de suite.
De cette façon, on descend dans l'arbre syntaxique jusqu'aux expressions, où on évalue les types en fonction des variables (dont on a mémorisé au préalable la déclaration, et donc le type).

## Gestion des tests

Pour lancer les tests, il faut exécuter le main de `src/mj/test/Test.java`.
Cette classe lance d'abord le vérificateur de type puis l'interpréteur sur les programmes du répertoire `tests/`, qui contient un sous dossier `error/` et un sous dossier `ok/`.

Le sous-dossier `error/` contient des programmes MiniJava qui doivent provoquer une erreur, soit lors de la vérification de type, soit lors de l'exécution par l'interpréteur.

Le sous-dossier `ok/` contient des programmes MiniJava qui doivent passer sans lever d'exceptions lors de la vérification de type ou à l'éxécution.

Ces programmes de test MiniJava contiennent des commentaires indiquant les fonctionnalités testées respectivement par chaque programme.
