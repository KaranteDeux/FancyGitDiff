# FancyGitDiff

## Informations générales

#### Par Antonin DUREY et Antonin PETIT
#### 12 Février 2017

## Implémentation

Notre code est découpé en 4 packages
1. git : Contient GitRepository, qui fait la liaison avec l'interface de JGit. Les méthodes *readElementsAt* et *readElementsChangedAt* sont utilisés pour récupérer les fichiers modifiés par un commit donné.
2. javafx : Contient les éléments graphiques devant être affichés et le Main de l'application. Nous avons ici séparé l'arborescence des cellules, les arrêtes, le layout, et les éléments généraux d'interfaces et d'évènements. La hiérarchie des cellules nous permet de traiter indifféremment les *ClassCell*, *MethodCell* ou *ParameterCell*. En Java FX il n'est pas possible d'écrire dans une *View* et nous avonc donc dû définir une view uniquement pour afficher le texte.
3. model : Contient les différents modèles (ClassModel, MethodModel, etc).
4. parser : Contient les 2 classes permettant de parser du java grâce au JavaParser. La classe *ClassVisitor* est appelée par gitRepository. *classVisitor* est un héritage de *VoidVisitorAdapter* définit par JavaParser. Cela permet de pouvoir être très spécifique dans les opérations à effectuer lorsqu'une classe ou interface est rencontré par l'analyseur d'arbre syntaxique.
