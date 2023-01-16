# ENSEIRB-Projet-Dev-Mobile

# Developpeurs : 
- Arnal Dorian
- Peyrat Mélanie

# Fonctionnalités

Les fonctionnalités de base ont été réalisées , c'est-à-dire l'affichage des catégories, des plats de ces catégories et de la recette.
En plus, nous avons ajouté 3 autres fonctionnalités : 
- au lieu d'avoir un lien pour la vidéo youtube nous avons directement mis la vidéo en bas de la page de la recette et elle se lance automatiquement quand la page est chargée.
- au lieu d'avoir seulement le noms et les quantités des ingrédients pour que cela soit plus agréable pour l'utilisateur nous avons mis aussi les icons des ingrédients.
- Nous avons aussi implémenté un bouton en bas à droite lors du choix de la catégorie qui donne une recette aléatoire.

# Difficultés
Nous avons eu des difficultés au départ pour charger une image grâce à son url en ligne mais après recherche nous avons réussi et le résultat se trouve dans ImageLoader.kt .
Ensuite, un problème que nous n'avons pas réussi à régler est le fait de pouvoir faire scroller un texte dans un TextView lui même dans un Recyclerview.
Nous avons eu également du mal à changer l'icon du bouton flottant.
De plus, la vidéo youtube a également posé des problèmes au niveau de la recherche car au départ nous pensé qu'il fallait une autorisation pour générer une clef pour se servir de l'API alors que non en dessous d'un certain nombre d'utilisation aucune autorisation est necessaire.
