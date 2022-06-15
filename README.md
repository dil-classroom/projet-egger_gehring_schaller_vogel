[![Build](https://github.com/dil-classroom/projet-egger_gehring_schaller_vogel/actions/workflows/maven.yml/badge.svg)](https://github.com/softwaremill/maven-badges/actions/workflows/ci.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/720cf7b88325d952ea25/maintainability)](https://codeclimate.com/github/dil-classroom/projet-egger_gehring_schaller_vogel/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/720cf7b88325d952ea25/test_coverage)](https://codeclimate.com/github/dil-classroom/projet-egger_gehring_schaller_vogel/test_coverage)

#### Date : 3 mars 2022

#### Membre du groupe : Maëlle Vogel, Mélissa Gehring, Joris Schaller, Magali Egger 


# Introduction
Bienvenu-e sur notre projet de semestre, nous avons pour projet d'enrichire ce document tout au long du semestre. 

# Objectif
L'objectif de ce projet de semestre est de pratiquer le développement logicielle en groupe ainsi que mettre en application les concepts théoriques étudiés en cours tel que Agil et Xtrem programming. 

# Fonctionnalité actuelle:
   - initialiser un site
   - nettoyer un site
   - générer un site
   - afficher la version du programme
# Roadmap :
Voici les prochaines étapes du développement : 
 - Tenir à jour ce read-me
 - Se préparer aux sprints suivants
 
# Licensing
Notre projet est sous [Licence MIT](https://github.com/dil-classroom/projet-egger_gehring_schaller_vogel/blob/main/LICENSE) 
#Spécification d'un fichier de contenu
Un fichier de contenu est un fichier dans le repertoire `content` qui contient du texte(utf8), l'extension du fichier est .md.
Le fichier est constitué de deux parties :
 - La première est du text au format yaml constituant les métadonnées.
 - La seconde est du texte au format markdown qui contient le contenu du fichier  
 - 
Les deux parties sont séparées par une ligne composée de trois tirets `---`. Si ce séparateur est omis, le fichier est considéré comme un fichier sans metadonnées.
En lançant la commande [init](./README.md#Commande-init), vous pouvez voir deux exemples de fichiers de contenu.
Attention si vous n'avez pas de métadonnées et ne mettez pas ---, vous ne pourrez pas avoir une ligne contenant ces tirets, puisqu'il sera interprété comme le séparateur.

# Instalation et lancement :
 - Télécharger la release du projet sur github.
 - Faire `unzip statique.zip` 
 - Faire `java -cp "statique/lib/*" Statique `

Vous devriez voir l'aide du programme affiché sur votre écran comme ci-dessous.

    Usage: statique [COMMAND]
    A brand new static site generator.
    Commands:
    init      Initialise un dossier pour le site statique
    clean     Clean a static site
    build     Build a static site
    serve     Serve a static site
    -version  Prints the version of the program


## Exemple d'utilisation
Nous supposons désormais que vous avez suivis l'étape Instalation et lancement et que vous êtes dans le dossier Statique.
Si ce n'est pas le cas faites 
```zsh
cd Statique
```
### Commande version

    java -cp "lib/*" Statique -version

Cette commande affiche la version du programme et ne prend pas d'argument.
### Commande init

    java -cp "lib/*" Statique -init mon/site/
Cette commande initialise un site statique dans le dossier `mon/site/`.
L'arborescence du site sera la suivante :

    mon/Site/
            content/
                    | - index.md
                    | - about.md
            templates/
                    | - header.html
                    | - footer.html
                    | - layout.html

**Argument** : le chemin du dossier où sera créé le site statique.

Le chemin doit être valide, mais les dossiers parents ne doivent pas forcément exister au préalable. 
Le dossier final ne doit pas exister au préalable, une erreur est affichée si c'est le cas.

### Commande build:  
Pour build un site situé dans `mon/site/` avec deux fichiers `index.md` et `about.md` dans le dossier `content`
un site initialisé implique que le dossier `template` ainsi que le fichier `template/layout.html` soit présent.
il suffit de faire :

    java -cp "lib/*" Statique build mon/site/
Et l'arborescence résultante est la suivante :
   
    mon/Site/
            | -content/ 
                      | - index.md
                      | - about.md
            | - build/
                      | - index.html
                      | - maPage.html
            | - templates/
                      | - header.html
                      | - footer.html
                      | - layout.html

Cette commande crée donc un dossier `build` dans le dossier `mon/site/` et y met la version html de tous les fichiers *.md du dossier `mon/site/content`.  
Le contenu des fichiers md est transformé en html puis l'html et les metadonnées sont injecté dans le layout.
On peut insérer les fichiers header et footer en utilisant la syntaxe suivante `{{>header}}` et `{{>footer}}` dans le layout.
Vous pouvez inclure de cette manière n'importe quel fichier html dans le layout, il suffit qu'il soit cans le dossier template 
et de l'inclure en utilisant son nom sans l'extension comme dans l'exemple ci-dessus.
**Argument** : un chemin vers un répertoire avec un site statique initialisé.

### Commande clean: 
    java -cp "lib/*" Statique clean mon/site/  
Cette commande supprime le dossier `build` du dossier `mon/site/` ainsi que le contenu du repertoire `build`.  
**Argument** : un chemin vers un répertoire avec un site statique initialisé.

# Audience
Ce projet est destiné a être évalué dans le cadre du cours DIL (développement d'ingénierie logicielle). 

# Code de conduite
Nous avons établit un code de conduite, qui décrit les comportements souhaités de la part des membres du groupe lors du travail sur le projet. [Code of conduct](https://github.com/dil-classroom/projet-egger_gehring_schaller_vogel/blob/main/code-of-conduct.md).

