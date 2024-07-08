import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String cheminFichier = args[0];

        try {
            int[] dimsPelouse = lireDimensions(cheminFichier);
            int largeurPelouse = dimsPelouse[0];
            int hauteurPelouse = dimsPelouse[1];
            List<Tondeuse> tondeuses = lireInformationsTondeuses(cheminFichier, largeurPelouse, hauteurPelouse);

            List<String> resultats = new ArrayList<>();
            for (int index = 0; index < tondeuses.size(); index++) {
                Tondeuse tondeuse = tondeuses.get(index);
                System.out.println("Tondeuse " + (index + 1) + " -> Position initiale: " + tondeuse.positionActuelle());
                tondeuse.afficherGrille("Position initiale");
                tondeuse.executerInstructions(tondeuse.instructions);
                resultats.add(tondeuse.positionActuelle());
                System.out.println("Tondeuse " + (index + 1) + " -> Position finale: " + tondeuse.positionActuelle() + "\n");
            }

            for (String resultat : resultats) {
                System.out.println(resultat);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] lireDimensions(String cheminFichier) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String[] dimensions = br.readLine().trim().split(" ");
            int largeurPelouse = Integer.parseInt(dimensions[0]);
            int hauteurPelouse = Integer.parseInt(dimensions[1]);
            return new int[]{largeurPelouse, hauteurPelouse};
        }
    }

    public static List<Tondeuse> lireInformationsTondeuses(String cheminFichier, int largeurPelouse, int hauteurPelouse) throws IOException {
        List<Tondeuse> tondeuses = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] positionInitiale = line.trim().split(" ");
                int x = Integer.parseInt(positionInitiale[0]);
                int y = Integer.parseInt(positionInitiale[1]);
                char orientation = positionInitiale[2].charAt(0);
                String instructions = br.readLine().trim();
                Tondeuse tondeuse = new Tondeuse(x, y, orientation, largeurPelouse, hauteurPelouse, instructions);
                tondeuses.add(tondeuse);
            }
        }
        return tondeuses;
    }
}
