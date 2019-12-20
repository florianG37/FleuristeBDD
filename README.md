
<h1>Application de gestion d'une boutique de fleuriste</h1>
<p><em> <strong>Auteurs : </strong>Florian GIGOT & Quentin LEVIEUX</em></p>
<h2>Introduction</h2>
<p><em>
Réalisation d'une application permettant à un fleuriste de gérer sa boutique.<br /><br />  Via cette application, un fleuriste peut gérer facilement ses articles, ses clients, ses fournisseurs, ses commandes et ses livraisons.<br /><br /> 
<strong>Temps pour développer le projet :</strong> 1 semaine <br />
<strong>Langage :</strong> JAVA (avec librairie JDBC) <br />
<strong>Interface :</strong> SWING <br />
<strong>Base de données :</strong> mySQL <br />
</em></p>

<h2>Informations pour lancer le projet</h2>
<p><em>
<ul>
<li>Déployer la base de données (fleuriste.sql)</li>     
<li>Ajouter le pilote au projet (mysql-connector-java-8.0.18.jar)</li>
</ul>
<strong>Pour exécuter le projet : dans Package Main se trouve main.java </strong>
</em></p>
<h2>Visuel de l'application</h2>
<em><strong>Video :</strong> Comment créer un client et passer une commande </em>

![ezgif com-video-to-gif-2](https://user-images.githubusercontent.com/57462792/70751951-e624dd00-1d31-11ea-9c67-1090d614b537.gif) 

<h2>Spécifications techniques</h2>
<h3>MCD de la base de données</h3> 

![mcd projet bdd](https://user-images.githubusercontent.com/45074223/71258937-9a78c180-2337-11ea-8fdb-9855ae5d1c02.JPG)
<h3>MLD de la base de données</h3> 

![mld](https://user-images.githubusercontent.com/45074223/71259176-1a9f2700-2338-11ea-8e5c-9bdce90467a9.JPG)

<h3>Modèle Général de l’architecture MVC du logiciel</h3>

![Structure](https://user-images.githubusercontent.com/45074223/71260011-1ffd7100-233a-11ea-971a-1105599de22e.JPG)
<em>
<ul>
  <li><strong>X</strong> représente le nom d’une classe dans le modèle</li>
  <li><strong>XController</strong> représente la classe qui fera l’interaction entre l’application et la BDD</li>
  <li><strong>XControllerView</strong> représente la classe qui gérera les boutons de la vue</li>
  <li><strong>XTableTemplate</strong> représente la classe qui gérera les JTables de la vue</li>
  <li><strong>XView</strong> représente la classe qui définira la vue</li>
</ul>
</em>
<h3>Modèle UML du logiciel</h3>

![uml model projet bdd](https://user-images.githubusercontent.com/45074223/71259201-2985d980-2338-11ea-82f5-70222022db29.JPG)


<h2>Informations complémentaires</h2>
<p><em>
  L’application permet de gérer les stocks de fleurs et de plantes d’une boutique d’un fleuriste. Cette gestion passe par 5 onglets : Produit, Client, Fournisseur, Commande, Fourniture. </br></br>
<strong>Pour utiliser les boutons Modifier et Supprimer veuillez cliquer sur un item avant de cliquer sur le bouton.</strong></br>
</em></p>
<h3>Produit</h3>
<em><ul>
  <li>Dans cette onglet, le propriétaire du magasin peut créer, modifier et supprimer un produit. Il dispose également d’un bouton Alerte, lui permettant de voir tous les produits en rupture de stock. Enfin, un bouton filtre et enlever filtre lui permettent de cibler des produits pour pouvoir les visualiser plus facilement.</li>
  <li>Une liste de produit est disponible pour la visualisation des produits</li>
</ul>
<h3>Client</h3>
<ul>
  <li>Dans cette onglet, le propriétaire du magasin peut créer, modifier et supprimer un client. Enfin, un bouton filtre et enlever filtre lui permettent de cibler des clients pour pouvoir les visualiser plus facilement.</li>
  <li>Une liste de client est disponible pour la visualisation des clients.</li>
  <li>Par ailleurs, un seul changement de pourcentage de réduction pour un client est disponible par jour.</li>
</ul>
<h3>Fournisseur</h3>
<ul>
  <li>Dans cette onglet, le propriétaire du magasin peut créer, modifier et supprimer un fournisseur.Un bouton affilier permet d’associer un produit à un fournisseur. Cette opération permet d’indiquer au logiciel qu’un fournisseur peut fournir un produit. Enfin, un bouton filtre et enlever filtre lui permettent de cibler des fournisseurs pour pouvoir les visualiser plus facilement.
</li>
  <li>Une liste de fournisseur est disponible pour la visualisation des fournisseurs.</li>
</ul>
<h3>Commande</h3>
<ul>
  <li>Dans cette onglet, le propriétaire du magasin peut créer et supprimer une commande. Par ailleurs en cliquant sur une commande on peut voir le détail d’une commande.</li>
  <li>Une liste de commande est disponible pour la visualisation des informations importantes liés à une commande.</li>
  <li>Une commande enlève automatiquement les produits (associés) du stocks.</li>
  <li>Une commande possède une date, ce qui permet de ce faire un historique de ces dernières. </li>
</ul>
<h3>Fourniture</h3>
<ul>
  <li>Dans cette onglet, le propriétaire du magasin peut créer et supprimer une fourniture. Par ailleurs en cliquant sur une fourniture on peut voir le détail d’une fourniture.</li>
  <li>Une liste de fourniture est disponible pour la visualisation des informations importantes liés à une fourniture.</li>
  <li>Une fourniture ajoute automatiquement les produits (associés) au stocks.</li>
  <li>Une fourniture possède une date et est associée à un fournisseur. Elle contient uniquement les produits que le fournisseur pouvait fournir à cette même date.</li>
</ul></em>
