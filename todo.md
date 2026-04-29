# implémenter la fusion-collision entre deux astres (done)
## Astre.verifyCollision(Astre) centre à contour / contour à centre (done)
## Astre.fusionner(Astre) crée un nouvel Astre (done)
- M = m + m'
- D = d + d'
- T = (d/d')t + (d'/d)t'
- P = (p + p')/2
- A = (ma + m'a') / (m + m')
- V = (mv + m'v') / (m + m')
- Nom = nom concat nom'
- Desc = (desc) + " fusionné avec " (desc')

# ajout de changement de coordonnées
Ajouter une translation des position, tout ramener au centre

# ajout de nouveux astres (héritage)
Ajout de Planet, Star, (Blackhole, whitehole...)

## Planet
- ajouter des attributs : viable, atmosphère...

## Star
- ajouter des attributs : type, couleur, stade...

## BlackHole
- ajouter des attributs : catégorie, rayon-horizon ...
- surcharger la fusion-collision

## WhiteHole
- ajout d'attributs: instabilité (0: annilation, 1: transformation en trou noir)
- surcharger la fusion-collision

# ajout zoom-in/zoom-out
Ajouter deux boutons "zoom-in" et "zoom-out" qui changent l'échelle

# ajout pause/play
Ajouter un bouton pause/play qui arrêtre/reprend l'animation et l'update des astres

# ajout de traces et vecteurs visuels
Afficher la vitesse, l'accélération, le tracés de trajectoire

# ajout déplacement de focus
Ajouter des flèches pour déplacer la caméra (monter, descendre, vers la gauche, la droite)
