# Portfolio - Groupe Egger, Gehring, Schaller, Vogel

## Introduction

Ce document contient le portfolio du projet de groupe dans le cadre du cours DIL 2022 de la HEIG-VD. Le projet est commissionné par l'équipe responsable du cours, et ils font donc office de ''client'' et de product owner.

Ce projet a pour but de créer un générateur de site statique. Le programme sera écrit en java et utilisera la librairie handlebars.

Ce projet met en pratique les méthodologies de gestion de projet vu pendant le cours de DIL notamment le processus agile et aussi les guidelines comme "commit early, commit often" ou encore les tests continues.


## Méthodologie

### Equipe 

Nous sommes une équipe composée de 4 personnes : Magali Egger, Mélissa Gehring, Joris Schaller et Maëlle Vogel. Nous avons une bonne synergie, nous entendons très bien et avons l'habitude de travailler ensemble. Notre groupe ne comporte à priori pas de leader, nous avons une hiérarchie horizontale.

Nous avons décidé de partir sur un processus logiciel agile semi-piloté afin d'avoir plus de liberté dans les étapes ultérieures du projet, nous préférons travailler de manière plus maléable.
Nous ne suivons pas exactement un framework existant. Nous participons tous ensemble à au moins une réunion par semaine afin d'organiser et de voir l'avancement du projet et des taches en cours. Lors de ces réunions, nous discutons des taches réalisées, des taches qu'ils restent à accomplir, des taches accomplies et des problèmes encourrus. En début de sprint, nous transformons également les stories utilisateurs en taches.

Nous communiquons régulièrement sur un groupe Telegram, et nous nous réunissons 1 fois par semaine afin de répartir les tâches entre nous. Certaines tâches sont difficilement réalisables par une seule personne, par exemple la rédaction du portfolio ou du README, et nous nous séparons donc en sous-groupes de 2 personnes afin d'être plus efficaces dans la réalisation desdites tâches.
Lorsqu'une personne a des problèmes pour réaliser sa tache, nous communiquons et nous entraidons afin de palier aux problèmes. 

Notre team est très complémentaire, nous avons une bonne connaissance des différents langages de programmation.
Certain d'entre nous avons déjà une expérience dans le développement de site web.
Melissa a déjà suivi un cours d'ingénierie logiciel ce qui nous fournit un avantage.
Joris a l'habitude de rechercher des solutions à des problèmes, des librairies déjà existantes, afin de ne pas réinventer la roue.
Maëlle est très organisée et nous aide à garder un oeil sur les deadlines.
Magali est touche-à-tout et est toujours là pour nous motiver et nous faire rire.


### Processus

Le déroulement typique d'une session peut être décrite ainsi :

* Nous discutons ensemble des tâches à réaliser, sélectionnons les premières user stories et créons les issues correspondantes
 * Chaque user story est discuté dans le groupe pour décider comment la sous-diviser en issue.  
* Nous attribuons 1 ou 2 personnes à chaque tâche, en équilibrant les charges autant que possible. La / les personne(s) assignée(s) estime(nt) le temps pessimiste, attendu et optimiste avant de commencer à travailler.
* Chaque personne (ou sous-groupe) commence à travailler sur sa tâche, en mettant à jour le kanban et en créant une nouvelle branche pour y travailler sans conflit sur la branche main. A la fin, l'équipe écrit des tests pour vérifier le fonctionnement de la feature.
* Une fois la tâche terminée et les tests rédigés, une pull request est créée et au moins une review est nécessaire avant de pouvoir merge la branche de la tâche sur la branche principale
* Après un review positive et la branche mergée, et on peut supprimer la branche et commencer à travailler sur la prochaine tâche


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

## Outils

### Kanban automatique (en liant les issues entre elles et en créant les pull requests qui ferment lesdites issues)

Nous avons utilisé un kanban pour organiser nos tâches avec les colonnes suivantes : 
 * Stories utilisateur : les stories utilisateurs sont les user stories qui nous permettent de définir les tâches/issues à réaliser.
 * Tâches à réaliser : Ce sont les étapes/issues qui résolvent les stories utilisateur(problèmes) qui ne sont pas encore en cours de réalisation ou réalisées. 
 * En cours : les tâches/issues qui sont en cours de réalisation.
 * Terminé : les tâches qui ont une PR qui a été validée et qui ont été merge. Cette colonne est automatiquement mise à jour.
 * Stories réalisées : les stories qui ont été séparées en plusieurs tâches qui ont toutes été validée et merge. Cette colonne n'est pas automatiquement mise à jour, puisque plusieurs taches peuvent etre liées à une story, il est difficile de l'automatiser.


## Troubleshooting

Cette section contient les différents problèmes que nous avons encourus, les solutions que nous avons trouvées pour y faire face, ainsi que les leçons que nous en avons tirées.

| **Problème**                                    | **Solution**                                                           | **Leçon**                                                                                                       |
|-------------------------------------------------|------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| Erreur dans le template d'exemple               | Reprendre un exemple correcte                                          | Si le code compile, il n'est pas forcement bon, vérifier le bon fonctionnement d'un effet de bord est compliqué |
| Oublier de signer le dernier commit dans une PR | faire un git commit --amend --signoff puis un push --force-with-lease. | Configurer la signature automatique : git config --global commit.gpgsign true                                   |
| Oublier de signer un ancien commit dans une PR  | Refaire une branche et prendre les changements                         | Configurer la signature automatique :  git config --global commit.gpgsign                                       |
| Oublier d'ajouter les estimations de temps      | Ajouter les estimations par après                                      | Etre plus rigoureux lors de l'étape d'assignation des taches                                                    |
| Push sur main n'était pas bloqué                | Chercher d'ou vient le problème sur StackOverflow                      | Nous devrions vérifier plus assidument nos mesures de sécurités lorsque nous les mettons en place               |

Nous avons décidé de mettre très tôt une cicd github afin d'automatiser les tests et de pouvoir faciliter les reviews.
Nous avons aussi choisis un style de code(Google) et l'avons rendu obligatoire dans la codebase à l'aide d'un teste du cicd.
Cependant, afin de ne pas avoir un commit qui formate toute la codebase, nous l'avons configuré de sorte à n'avoir le teste que sur les fichiers différents de 
la branche main ainsi, une personne peut commit et push (et merge si sa PR répond aux exigences) sans être bloqué par la pipeline cicd à cause de fichiers
non modifiés qui ne sont pas correctement formaté.

## Sprint 1

Avant de commencer ce sprint, nous avons évalué nos possibilités pour fournir un outil capable de générer un site statique, l'utilisation d'un moteur de template
comme [mustache](https://github.com/spullara/mustache.java) a été envisagé, mais après discussion, nous avons trouvé que pandoc répondait mieux aux exigences et était plus
simple à prendre en main. Pandoc permet de générer des fichiers html à partir d'un fichier md suivant un template précis. Ceci nous a permis de regrouper les 3 premières stories en une seule task, à savoir d'utiliser Pandoc.

Nous avons aussi remarqué, lors du découpage des stories en issues, que c'était plus compliqué que prévu et que le découpage d'un problème réel en une solution informatique
créait des conflits d'organisation. En effet, lors de ce premier sprint les stories étaient simples et nous avons donc eu 1 seule issue par story, Nous avons donc eu 1 issue et 1 user story pour une tâche,
grâce à notre règle de mettre dans la PR close #n, l'issue était fermée mais la story pas forcément.
Le découpage a été immédiatement fait au début alors que nous aurions pu attendre un peu et le faire de manière incrémentale, c'est ce que nous ferons les prochaines fois.

Nous avons réalisé par erreur que nos mesures de sécurités pour empecher de push sur la branche main sans passer par des pull requests ne fonctionnait pas. Il fallait ajouter cette règle aux administrateurs également, ce que nous n'avions pas fait.

Nous n'avons pas écrit de tests avant de commencer à implémenter les features, nous les avons faits par après pour en vérifier le fonctionnement. Nous envisageons de réaliser des tests avant de commencer à programmer pour les prochains sprints.

## Sprint 2

### Etat actuelle du projet

Nous sommes passé de pandoc à handelbars car avec l'ajout des templates l'utilisation de pandoc devenait compliquée.
La commande build a été mise à jour pour utiliser handelbar et prendre en compte les templates.

Hélàs la continuous delivery n'a pas pu être mise en place par manque de temps.

### Comparaison des temps attendus et actuelles

Nous avons souvent pris plus de temps qu'attendu car nous avons plusieurs projets en parralléle. Toujours devoir se replonger dans le code nous fais perdre du temps.

### Ce qui pourrait être amélioré

Nous pourrions tenir à jour le portfolio plus souvent pour éviter de devoir tout écrire au dernier moment.
Les tests devraient être écrit avant la rédaction du code. Ou du moins un pseudo-code pour savoir à quoi s'attendre.

### Ce qui doit être continué pendant le prochain sprint

Nous devons encore ajouter la continuous delivery.
