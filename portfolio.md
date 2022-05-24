# Portfolio - Groupe Egger, Gehring, Schaller, Vogel

## Introduction 

### Présentation du contexte

Ce document contient le portfolio du projet de groupe dans le cadre du cours DIL 2022 de la HEIG-VD. Ce cours a pour but de nous faire découvrir les processus de développement en ingénierie logicielle. Notamment les processus de développement, les processus agiles, les spécifications, la conception et modélisation, la gestion des configurations et les tests, validation et performances des logiciels. C'est donc dans ce contexte que nous allons développer notre projet, qui a pour but de créer un générateur de site statique. Le projet est commissionné par l'équipe responsable du cours, M. Chapuis (Professeur) et M. Santamaria (Assistant) et ensemble ils font donc office de client et de product owner. 


### Objectif

Ce projet a pour but de mettre en pratique les méthodologies de gestion de projet vu pendant le cours de DIL notamment le processus agile. Ce processus change la dynamique de communication entre le client et l'équipe. En effet, dans les approches agiles, des itérations apparaissent entre les activités. La définition
des besoins, le design, l’implémentation et les tests s’entremêlent et s’influencent.

Ce projet développe aussi les notions suivantes : les guidelines comme "commit early, commit often" ou encore les tests continues. 


### Présentation du projet

Le programme sera écrit en java. Il inclut plusieurs librairies dont handlebars qui compile des template en fonction JavaScript (templating). Mais également commons-io de apache pour copier des répertoires, snakeyaml pour parser les meta données en yaml et enfin flexmark-all pour transformer le contenu markdown en html. Nous avons choisi nos librairies principalement selon les critères selon le nombre de star sur github, mais aussi selon notre compréhension du getting started, ainsi que la date du dernier commit. Car ces éléments nous permettent de nous situer par rapport aux différents choix de librairie possibles.

Nous avons également utilisé les librairies proposé directement par le client comme JaCoCo qui permet de calculer le degré de couverture du code par des tests unitaires et d’intégration, ou encore sonarQube afin de détecter des bugs et des vulnérabilités.



### Présentation de l'équipe 

Nous sommes une équipe composée de 4 personnes : Magali Egger, Mélissa Gehring, Joris Schaller et Maëlle Vogel. Nous avons une bonne synergie, nous entendons très bien et avons l'habitude de travailler ensemble. Notre groupe ne comporte à priori pas de leader, nous avons une hiérarchie horizontale.

Notre team est très complémentaire, nous avons une bonne connaissance des différents langages de programmation. Certain d'entre nous avons déjà une expérience dans le développement de site web. Melissa a déjà suivi un cours d'ingénierie logiciel ce qui nous fournit un avantage. Joris a l'habitude de rechercher des solutions à des problèmes, des librairies déjà existantes, afin de ne pas réinventer la roue. Maëlle est très organisée et nous aide à garder un oeil sur les deadlines. Magali est touche-à-tout et est toujours là pour nous motiver et nous faire rire.


### Organisation au sein de l'équipe

Nous avons décidé de partir sur un processus logiciel agile semi-piloté afin d'avoir plus de liberté dans les étapes ultérieures du projet, nous préférons travailler de manière plus malléable.

Nous ne suivons pas exactement un framework existant. Nous participons tous ensemble à au moins une réunion par semaine afin d'organiser et de voir l'avancement du projet et des taches en cours. Lors de ces réunions, nous discutons des taches réalisées, des taches qu'ils restent à accomplir, des taches accomplies et des problèmes encourus. En début de sprint, nous transformons également les stories utilisateurs en tâches.

Nous communiquons régulièrement sur un groupe Telegram, et nous nous réunissons 1 fois par semaine afin de répartir les tâches entre nous. Certaines tâches sont difficilement réalisables par une seule personne, par exemple la rédaction du portfolio ou du README, et nous nous séparons donc en sous-groupes de 2 personnes afin d'être plus efficaces dans la réalisation desdites tâches.
Lorsqu'une personne a des problèmes pour réaliser sa tâche, nous communiquons et nous nous entraidons afin de palier aux problèmes. 



## Méthodologie

### Description d'une session 

Le déroulement typique d'une session peut être décrite ainsi :

* Nous discutons ensemble des tâches à réaliser, sélectionnons les premières user stories et créons les issues correspondantes
 * Chaque user story est discuté au sain du groupe 
 * Nous emmetons ensuite des hypothèses sur les fonctionnalités tehniques nécessaire à l'implémentation de chaque stories
 * Nous décidons ensuite des issues qui seront crée à partir de chaque story.
* Nous attribuons 1 ou 2 personnes à chaque tâche, en équilibrant les charges autant que possible. La / les personne(s) assignée(s) estime(nt) le temps pessimiste, attendu et optimiste avant de commencer à travailler.
* Chaque personne (ou sous-groupe) commence à travailler sur sa tâche, en mettant à jour le kanban et en créant une nouvelle branche pour y travailler sans conflit sur la branche main. A la fin, l'équipe écrit des tests pour vérifier le fonctionnement de la feature.
* Une fois la tâche terminée et les tests rédigés, une pull request est créée et au moins une review est nécessaire avant de pouvoir merge la branche de la tâche sur la branche principale
* Après un review positive et la branche mergée, et on peut supprimer la branche et commencer à travailler sur la prochaine tâche


### Choix des guidelines techniques

Le nom de branche doit être clair et faire écho à la tâche qu'elle concerne. Nous utilisons en principe le format suivant pour nos branches : feature_XXX pour les features, et bug_XXX pour les bugs.

Chaque tâche doit être accompagnée de documentation complète et de tests.

Nos pull requests doivent en principe répondre aux questions suivantes:

* Qu'est-ce que cette PR va changer ?
* Pourquoi l'a-t-on changé ?
* Comment l'a-t-on testé ?
* Est-ce que cette feature va encore évoluer ?

La PR doit être accompagnée d'un "Closes #n" ou n correspond au numéro de l'issue concernée, afin qu'elle soit fermée automatiquement au moment où la PR est merge, afin de garder noter kanban à jour.
Les sprints imposés par le cours nous donne le cadre agile de la même manière que si nous interagissions avec un client.
Nos réunions ont lieux de manière hebdomadaires durant les heures de laboratoires. Nous faisons des petits meetings au Chill de manière fortuite ou par l'intermédiaire de Telegram.
Les PR doivent avoir des commits signés.
Les PR doivent être accompagnée de tests unitaires portant sur les nouvelles fonctionnalités et avoir le code formaté selon les conventions Google.

Nous allons utiliser un système de releases pour déployer notre logiciel.

En principe nous rédigeons toute la documentation en français. 

Le format des commits dépend du type de tâche effectuées. Néanmoins nous essayons au tant que possible de garder la forme suivante : verbe suivie suivie de l'action ou d'une précision. Sauf dans les cas évidents, comme par exemple lors de l'ajout de documentation. En prenant un peu de recule sur cette question, et surtout en observant nos commits dans les logs, nous nous sommes rendu compte que nos commit ne sont pas toujours précis et en plus il ne sont pas du tout uniforme. Nous avons donc des progrès à faire sur le nommage de nos commits. Par exemple, avec une structure fixée dès le début, nous aurions pu avoir des commits beaucoup plus clair et beaucoup plus uniforme. 


## Outils

### Kanban automatique 

Le kanban a été automatisé en liant les issues entre elles et en créant les pull requests qui ferment lesdites issues.

Nous avons utilisé un kanban pour organiser nos tâches avec les colonnes suivantes : 
 * Stories utilisateur : les stories utilisateurs sont les user stories qui nous permettent de définir les tâches/issues à réaliser.
 * Tâches à réaliser : Ce sont les étapes/issues qui résolvent les stories utilisateur(problèmes) qui ne sont pas encore en cours de réalisation ou réalisées. 
 * En cours : les tâches/issues qui sont en cours de réalisation.
 * Terminé : les tâches qui ont une PR qui a été validée et qui ont été merge. Cette colonne est automatiquement mise à jour.
 * Stories réalisées : les stories qui ont été séparées en plusieurs tâches qui ont toutes été validée et merge. Cette colonne n'est pas automatiquement mise à jour, puisque plusieurs taches peuvent être liées à une story, il est difficile de l'automatiser.

## tableau reliant les stories au issues

Cette section contiendra un tableau reprenant chaque storie et qui montrera les différentes issues qui y sont liées ainsi que le temps estimé et le temps nécessaire à chaque issue. L'objectif est d'avoir ces résultats regroupés afin de pouvoir faire une analyse plus appronfondie du temps nécessaire aux différentes tâches et le degré de complexité des issues de manière à pouvoir avoir un retour sur les parties qui ont le plus posées problème et celle qui au contraire, ce sont déroulées avec facilité.


## Troubleshooting

Cette section contient les différents problèmes que nous avons encourus, les solutions que nous avons trouvées pour y faire face, ainsi que les leçons que nous en avons tirées.

| **Problème**                                    | **Solution**                                                           | **Leçon**                                                                                                       |
|-------------------------------------------------|------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| Erreur dans le template d'exemple               | Reprendre un exemple correcte                                          | Si le code compile, il n'est pas forcement bon, vérifier le bon fonctionnement d'un effet de bord est compliqué |
| Oublier de signer le dernier commit dans une PR | faire un git commit --amend --signoff puis un push --force-with-lease. | Configurer la signature automatique : git config --global commit.gpgsign true                                   |
| Oublier de signer un ancien commit dans une PR  | Refaire une branche et prendre les changements                         | Configurer la signature automatique :  git config --global commit.gpgsign                                       |
| Oublier d'ajouter les estimations de temps      | Ajouter les estimations par après                                      | Etre plus rigoureux lors de l'étape d'assignation des taches                                                    |
| Push sur main n'était pas bloqué                | Chercher d'ou vient le problème sur StackOverflow                      | Nous devrions vérifier plus assidument nos mesures de sécurités lorsque nous les mettons en place               |



## Retour sur le Sprint 1

### Choix technique

Avant de commencer ce sprint, nous avons évalué nos possibilités pour fournir un outil capable de générer un site statique, l'utilisation d'un moteur de template
comme [mustache](https://github.com/spullara/mustache.java) a été envisagé, mais après discussion, nous avons trouvé que pandoc répondait mieux aux exigences et était plus
simple à prendre en main. Pandoc permet de générer des fichiers html à partir d'un fichier md suivant un template précis. Ceci nous a permis de regrouper les 3 premières stories en une seule task, à savoir d'utiliser Pandoc.
Nous n'avons pas écrit de tests avant de commencer à implémenter les features, nous les avons faits par après pour en vérifier le fonctionnement. Nous envisageons de réaliser des tests avant de commencer à programmer pour les prochains sprints.

### Organisation des issues

Nous avons aussi remarqué, lors du découpage des stories en issues, que c'était plus compliqué que prévu et que le découpage d'un problème réel en une solution informatique créait des conflits d'organisation. En effet, lors de ce premier sprint les stories étaient simples et nous avons donc eu une seule issue par story, Nous avons donc eu une issue et une user story pour une tâche. Grâce à notre règle de mettre dans la PR close #n, l'issue était fermée mais la story pas forcément.
Le découpage a été immédiatement fait au début alors que nous aurions pu attendre un peu et le faire de manière incrémentale.

### Gestion du repository

Nous avons réalisé par erreur que nos mesures de sécurités pour empecher de push sur la branche main sans passer par des pull requests ne fonctionnait pas. Il fallait ajouter cette règle aux administrateurs également, ce que nous n'avions pas fait.

### Continuous Integration Continuous Delivery

Nous avons décidé de mettre très tôt une cicd github afin d'automatiser les tests et de pouvoir faciliter les reviews.
Nous avons aussi choisis un style de code(Google) et l'avons rendu obligatoire dans la codebase à l'aide d'un teste du cicd.
Cependant, afin de ne pas avoir un commit qui formate toute la codebase, nous l'avons configuré de sorte à n'avoir le test que sur les fichiers différents de la branche main ainsi, une personne peut commit et push (et merge si sa PR répond aux exigences) sans être bloqué par la pipeline cicd à cause de fichiers non modifiés qui ne sont pas correctement formaté.

## Retour sur le Sprint 2

### Etat actuelle du projet

Nous sommes passé de pandoc à handelbars car avec l'ajout des templates l'utilisation de pandoc devenait compliquée.
La commande build a été mise à jour pour utiliser handelbar et prendre en compte les templates.

Hélàs la continuous delivery n'a pas pu être mise en place par manque de temps. De plus, nous n'avons pas pu implementé la commande init, car la personne en charge de cette issue s'est trouvé submergé par d'autres projets (Baleinev) et n'a donc pas pu terminer cet aspect du sprint. Néanmoins, nous avons pu mener à bien ce sprint grâce à l'investissement et le travail accomplie par le groupe 

### Methode de conception 

Lors de ce sprint, nous avons tenté de mettre en place une méthode de conception, en commençant par brain-stormer sur le projet à l'aide de petites notes et de croquis dans le but d'explorer plusieurs idées, par exemple sur les outils à utiliser ou encore sur l'architecture des dossiers. La plupart des idées émisent ont finalement été abandonnées mais cette méthodes nous a au moins permis de nous mettre d'accord sur certain aspect du projet. 
Nous n'avons pas beaucoup travaillé avec la notion de décomposition mais avec du recul, nous avons réalisé que nous cela nous aurait aidé de décomposer le projet. Notamment en modélisant les interactions des différents composants. Par exemple à l'aide d'un diagramme de séquence. 

### Refactoring

Nous nous en sommes plutôt bien sortie avec l'aspect de refactoring du code. Cela peut être une opération problématique si elle n'est pas faite consciencieusement. Il n'est pas toujours évident de changer un code déjà implementé dans le but de le réutiliser dans un autre projet. Cela nous a demandé de nous y préparer en amont de réflechir à ces questions avant de coder. Cela a été intéressant de constater que nous n'étions pas tout de suite d'accord sur la façon de procéder. L'outil de refactoring d'intellij nous a été très utile. En effet, il est simple à utiliser et permet d'éviter que le code devienne un véritable champs de bataille, lors de ce processus.

### Comparaison des temps attendus et actuelles

Nous avons souvent pris plus de temps qu'attendu car nous avons plusieurs projets en parralléle. Toujours devoir se replonger dans le code nous fais perdre du temps. Nous commençons à tirer des leçons concernant la gestion de temps de travail. En effet, nous avons réalisé que nous sous-estimons le temps nécessaire pour une issue, car nous ne prenons pas suffisamment en compte les problèmes potentiels. De plus, nous ne considérons pas toujours le temps nécessaire à l'acquisition de nouvelle compétence, ainsi que les délais engendrés par le fait de jungler entre plusieurs projets. 

### Automatisation

Nous avons exploré l'automatisation en mettant en place un système automatique pour les messages des commits. Beaucoup d'autre aspect du projet aurait pu être automatisé. Mais nous avons craint que la mise en place de ces automatisations prennent plus de temps que de continuer le projet sans cela. Ce choix est difficile car il nécessite d'avoir une bonne connaissance des besoins à venir du projet ainsi que sa temporalité.

### Ce qui pourrait être amélioré

Nous pourrions tenir à jour le portfolio plus souvent pour éviter de devoir tout écrire au dernier moment. En effet, nous sommes souvent plus concentrés sur le code que sur la gestion du projet en lui-même. Ce qui peut engendrer des problèmes organisationnels et ainsi nuire à la qualité du travail fourni. 
Les tests devraient être écrit avant la rédaction du code. Une bonne solution pourrait être de les écrire en pseudo-code pour savoir à quoi s'attendre et ainsi avoir une meilleure idée de ce à quoi nous attendre. 

### Ce qui doit être continué pendant le prochain sprint

Nous devons encore ajouter la continuous delivery. 



## Retour sur le Sprint 3

### Conception incrémentale
Lors de ce sprint, nous avons pu intégrer des méthodes de conception incrémentale notamment grâce à l'utilisation du diagramme de classe. Nous avons fait le choix d'utiliser l'outil d'intellij qui permet de générer un diagramme automatiquement à partir du code. Nous avons trouvé cette solution pratique, car elle est très rapide à mettre en place et permet de bien visualiser l'organisation et l'architecture du code. 

![image](Image/Diagramme_classe.png)

### Refactoring

En ce qui concerne le refactoring nous avons eu plus de difficulté qu'au sprint 2. En effet, le refactoring est un processus long à mettre en place, et lors de ce sprint nous avons eu moins de temps disponible à consacrer à l'avancement du projet. 

### Test d'intégration et test système

Concernant les tests, nous avons établi que les pull request ne seraient acceptées que si elle contiennent des tests. Ce mécanisme permet donc de être sûr que toutes les fonctionnalités soient testées.
Nous avons rajouté un test qui effectue plusieurs commande à la suite notamment : init build clean. Ce test fonctionne ce qui montre que les commandes ont un comportement normale. Nous avons aussi implementé un test qui effectue seulement la commande build pour vérifier qu'effectuer cette commande seul ne fonctionne pas. 

### Automatisation

Nous avons mis en place github Action afin de faire la release automatique au moment où un membre de l'équipe push un tag (la pull request a été squash and merge). Il liste tous les nouveaux commits depuis la dernière release et il prépare le jar afin de l'ajouter à la release afin d'obtenir le même résultat que lorsque nous faisions les releases à la main. 

### Etat actuel du projet

Actuellement la watch service fonctionne et les commandes build et serve l'ont implementée. Afin de proposer une interface simple et concise, nous avons imaginé une interface que la classe Watch implémente. L'interface proposée est minimaliste et permet de caché la complexité. Puisque les classes de commandes sont des Callable<Integer>,
nous avons décidé de proposé une fonction qui prend en paramètre un chemin et un Callable, la méthode call de ce dernier est appelée lorsque un changement est détecté dans le dossier indiquer par le chemin ou un sous répertoire.
Nous avons rencontré un problème avec le template de pulls request. Il ne s'applique pas comme il le devrait. Nous n'avons donc pas pu utiliser un template de pull request à remplir. Ce qui nous a empêché de faire certaine pulls request. Ce bug a impliqué que nos pulls request n'ont pas toute le même format.
  
### A continuer au prochain sprint
  
Nous n'avons pas pu avancer beaucoup sur nos tâches. Cela est dû au manque de temps et à la quantité de travail qui devient de plus en plus importante. 
Etant donné la situation, nous nous sommes réuni pour discuter des problématiques rencontré lors de ce sprint. Nous avons cerné le fait que ce sprint 3 n'avait pas été mis suffisament en priorité par rapport à d'autres tests et laboratoires et que cela devait changer afin que le travail fournit au sprint 4 puisse être suffisant. 
Nous allons donc reporter sur le prochain sprint les stories suivantes : 
- code benchmarking
- code quality
- code coverage
- Javadoc
- Manuel utilisateur
- Publication du site dans un répertoire distant

Nous aimerions également rajouter dans le cadre de la continous integration continous delivery le fait de pouvoir faire tourner notre programme sur différents OS. Actuellement, nous utilisons uniquement ubuntu. Mais nous aimerions rajouter windows, mac, et d'autres.
