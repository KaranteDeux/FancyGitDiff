# FancyGitDiff

## Informations g�n�rales

#### Par Antonin DUREY et Antonin PETIT
#### 12 F�vrier 2017

## Impl�mentation

Notre code est d�coup� en 4 packages
1. git : Contient GitRepository, qui fait la liaison avec l'interface de JGit. Les m�thodes *readElementsAt* et *readElementsChangedAt* sont utilis�s pour r�cup�rer les fichiers modifi�s par un commit donn�.
2. javafx : Contient les �l�ments graphiques devant �tre affich�s et le Main de l'application. Nous avons ici s�par� l'arborescence des cellules, les arr�tes, le layout, et les �l�ments g�n�raux d'interfaces et d'�v�nements. La hi�rarchie des cellules nous permet de traiter indiff�remment les *ClassCell*, *MethodCell* ou *ParameterCell*. En Java FX il n'est pas possible d'�crire dans une *View* et nous avonc donc d� d�finir une view uniquement pour afficher le texte.
3. model : Contient les diff�rents mod�les (ClassModel, MethodModel, etc).
4. parser : Contient les 2 classes permettant de parser du java gr�ce au JavaParser. La classe *ClassVisitor* est appel�e par gitRepository. *classVisitor* est un h�ritage de *VoidVisitorAdapter* d�finit par JavaParser. Cela permet de pouvoir �tre tr�s sp�cifique dans les op�rations � effectuer lorsqu'une classe ou interface est rencontr� par l'analyseur d'arbre syntaxique.
