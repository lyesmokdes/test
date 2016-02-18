Ce document a pour but d'établir certaines règles  de syle de programation
pour le projet. 

## Les commentaires de début de fichier :

 Chaque fichier source devrait commencer par un commentaire multi-lignes 
 contenant au minimum des informations sur le nom de la classe, la description,  
 l'auteur et éventuellement d'autres commentaires utiles.

 Exemple :  

    /*
    * Nom de la classe : MaClasse
    *
    * Description   : description de la classe et de son rôle
    *
    * Auteur        : l'auteur
    *
    */

## Les clauses concernant les packages :

 La première ligne de code du fichier devrait être  une clause package
 indiquant à quel paquetage appartient la classe.

 
## Les commentaires pour une méthode :

    /*
    * Description   : description de la methode et de son rôle.
    *
    * @param	   arg1 description du 1er argument
    *    :             :               :	
    * @param	   argN description du Neme argument
    * @return	   description de la valeur de retour
    *
    */
 
 
## Indentation :
 
 Quatre espaces doivent être utilisés comme unité d' indentation.
 
## Longueur de la ligne :

 Éviter les lignes de plus de 80 caractères. 

## La coupure de ligne :

 Lorsqu'une expression ne tient pas sur une seule ligne , le couper en fonction
 de ces principes généraux :
    *couper la ligne après une virgule ou avant un opérateur.
    *aligner le début de la nouvelle ligne au début de l'expression coupée.
    *Si les règles ci-dessus conduisent à un code source en confusion ,
  un retrait de 8 espaces au debut de ligne à la place.
 
## Déclarations :

 Une déclaration par ligne.


## Conventions de nommage :

### Classes :
 Les noms de classes doivent être des noms , en cas des 
 nom composés la première lettre de chaque mot est en majuscule.  

### Methodes : 
 Les méthodes doivent commencer par un verbes, en cas d'un nom composé
 la première lettre est en minuscule , et la première lettre de chaque mot
 interne est en majuscule.

### Variables :
 Commence par une lettre minuscule, en cas de nom composé la premièère lettre
 en minuscule et les mots internes en majuscule.

### Constantes :
 Les noms des variables déclarées constantes de classe doivent être tout
 en majuscules avec des mots séparés par des underscores.

## Les espaces :
 
 Un espace vide devrait toujours être utilisé dans les cas suivants :
    *entre un mot clé et une parenthèse. 
    *après chaque virgule dans une liste d'argument.
    *tous les opérateurs binaires doivent avoir un blanc qui les précèdent et
   qui les suivent.
  
