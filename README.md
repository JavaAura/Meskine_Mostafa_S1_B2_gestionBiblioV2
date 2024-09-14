# Jiblio_v2

## Description du projet
Jiblio_v2 est une évolution et amélioration d'une application de gestion de bibliothèque. Cette version intègre la persistance des données ainsi que des fonctionnalités avancées pour gérer une variété de documents tels que les livres, magazines, journaux scientifiques et thèses universitaires.

## Objectif général de l'application
L'objectif principal de Jiblio_v2 est de faciliter la gestion d'une bibliothèque, en permettant aux utilisateurs de :
- Ajouter, modifier, et supprimer des documents.
- Filtrer et rechercher des documents par type, auteur, ou année.
- Gérer les informations relatives aux professeurs et aux étudiants.
- Assurer la persistance des données avec une base de données.

## Technologies utilisées
- **Java** : Langage principal utilisé pour le développement de l'application.
- **Streams Java** : Manipulation des données des documents.
- **Base de données** : Pour stocker les informations des documents et des utilisateurs.
- **JDBC** : Pour la connexion à la base de données.
- **Git** : Système de contrôle de version pour suivre les modifications du projet.

## Structure du projet
- **DAO/** : Contient les classes pour l'accès aux données et la gestion de la persistance.
- **metier/** : Contient les classes métier qui encapsulent la logique d'affaires.
- **presentation/** : Contient les classes pour l'interface utilisateur et la gestion de l'interaction avec l'utilisateur.
- **utilitaire/** : Contient les classes utilitaires et les fonctions réutilisables.

## Description brève de l'architecture adoptée
L'architecture adoptée est organisée en couches :
- **DAO (Data Access Object)** : Gère l'interaction avec la base de données.
- **Metier** : Contient la logique métier de l'application.
- **Presentation** : Fournit une interface utilisateur pour interagir avec l'application.
- **Utilitaire** : Contient des outils et services auxiliaires pour le projet.

## Instructions d'installation et d'utilisation
### Prérequis
- **Java 8** ou version supérieure.
- **JDK** installé sur votre machine.
- Un serveur de base de données SQL (par ex. postgreSQL).

### Étapes pour configurer la base de données
1. Créez une base de données nommée `jiblio_v2`.
2. Importez les fichiers SQL pour créer les tables.

### Comment lancer l'application
1. Clonez ce dépôt avec la commande :
   ```bash
   git clone https://github.com/votre_nom/jiblio_v2.git

## Planification Jira
- https://meskinemsoatafa.atlassian.net/jira/software/projects/JB/boards/8