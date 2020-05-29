CAI Yunfan
					

				SCHECULE

Ce logiciel peut s'utiliser d'un seulement manières différentes :  
	- avec un seul argument , le nom de csv


On peut alors lancer le logiciel grâce à :
	→  java -jar DM2.jar ex2_td.csv
Cette ligne va permettre de creer un fichier ex2_td_solved.csv sous dossir reesource

Il faut mettre le fichier que vous voulez tester dans dossier ressource


La class principal se situe dans src/Main

Ce sont les liens pour récupérer mon code.



Par exemple :
java -jar DM2.jar ex1_td.csv
java -jar DM2.jar ex2_td.csv

pour le ficheir ex4_huge c'est un peut grand je vous conseille de utiliser la commande spécial si il n'est pas executable pour la commande
java -jar DM2.jar ex4_huge.csv
Résolution:
java -jar -Xms1024m -Xmx10240m DM2.jar ex4_huge.csv

Remarque: pour récupère le temps pour correction je directement vous donne mon produit pour fichier ex3_big.csv dan dossier ressource
ça coute moins de 1 minute pour obtenir.
mais pour ex4_huge, il y a 50000 donc c'est très grand, je seulement teste il fonction pour ce fichier 