package compteurcarac;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MainSansExecutor {

        public static void main(String[] args) throws InterruptedException {
                // Liste des tâches (les URLs à analyser)
                List<CompteurDeCaracteresRunnable> taches = List.of(
                                new CompteurDeCaracteresRunnable("http://www.univ-jfc.fr"),
                                new CompteurDeCaracteresRunnable("https://www.irit.fr/"),
                                new CompteurDeCaracteresRunnable("http://www.google.fr"),
                                new CompteurDeCaracteresRunnable("https://www.netflix.com/browse"),
                                new CompteurDeCaracteresRunnable("https://nodejs.org/fr"));

                Instant start = Instant.now();

                // TODO : créer et démarrer un Thread pour chaque tâche
                List<Thread> threads = new ArrayList<>();

                for (CompteurDeCaracteresRunnable t : taches) {
                Thread th = new Thread(t); 
                th.start();                
                threads.add(th);           
                }

                // TODO : attendre la fin de tous les threads avec join()

                for (Thread th : threads) {
                th.join(); 
}

                // Tip : vous pouvez au préalable stocker les threads dans une liste

                int totalCaracteres = 0;
                Duration sommeDesTemps = Duration.ZERO;

                // TODO : récupérer les résultats de chaque tâche et accumuler
                // - nombre total de caractères
                // - somme des temps individuels
                // - pour faire une somme de Duration, utiliser la méthode plus()
                for (CompteurDeCaracteresRunnable t : taches) {
                        ResultatDuCompte r = t.getResultat();
                        if (r != null) {
                                totalCaracteres += r.nombreDeCaracteres;          
                                sommeDesTemps = sommeDesTemps.plus(r.tempsDeCalcul); 
                        }
                }

                System.out.printf("Nombre total d'octets : %d %n", totalCaracteres);
                System.out.printf("Temps effectif de calcul ~ %d secondes %n",
                                Duration.between(start, Instant.now()).toSeconds());
                System.out.printf("Somme des temps individuels ~ %d secondes %n",
                                sommeDesTemps.toSeconds());
        }
}
