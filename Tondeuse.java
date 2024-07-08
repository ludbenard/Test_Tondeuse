class Tondeuse {
    private static final char[] DIRECTIONS = {'N', 'E', 'S', 'W'};
    private static final char[] ARROWS = {'↑', '→', '↓', '←'};

    private int x, y;
    private char orientation;
    private int largeurPelouse, hauteurPelouse;
    public String instructions;

    public Tondeuse(int x, int y, char orientation, int largeurPelouse, int hauteurPelouse, String instructions) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.largeurPelouse = largeurPelouse;
        this.hauteurPelouse = hauteurPelouse;
        this.instructions = instructions;
    }

    public void avancer() {
        switch (orientation) {
            case 'N':
                if (y < hauteurPelouse) y++;
                break;
            case 'E':
                if (x < largeurPelouse) x++;
                break;
            case 'S':
                if (y > 0) y--;
                break;
            case 'W':
                if (x > 0) x--;
                break;
        }
        afficherGrille("Avancer");
    }

    public void tournerGauche() {
        int currentIndex = indexOf(DIRECTIONS, orientation);
        orientation = DIRECTIONS[(currentIndex + 3) % 4];
        afficherGrille("Tourner à Gauche");
    }

    public void tournerDroite() {
        int currentIndex = indexOf(DIRECTIONS, orientation);
        orientation = DIRECTIONS[(currentIndex + 1) % 4];
        afficherGrille("Tourner à Droite");
    }

    public void executerInstructions(String instructions) {
        for (char instruction : instructions.toCharArray()) {
            switch (instruction) {
                case 'A':
                    avancer();
                    break;
                case 'G':
                    tournerGauche();
                    break;
                case 'D':
                    tournerDroite();
                    break;
            }
        }
    }

    public String positionActuelle() {
        return x + " " + y + " " + orientation;
    }

    public void afficherGrille(String action) {
        System.out.println("\nAction: " + action);
        for (int y = hauteurPelouse; y >= 0; y--) {
            for (int x = 0; x <= largeurPelouse; x++) {
                if (x == this.x && y == this.y) {
                    System.out.print(ARROWS[indexOf(DIRECTIONS, orientation)] + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private int indexOf(char[] array, char value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) return i;
        }
        return -1;
    }
}
