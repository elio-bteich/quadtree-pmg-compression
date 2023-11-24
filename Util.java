import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Util{


    /**
    * Vérifie si un nombre est une puissance de 2.
    *
    * @param nombre Le nombre à vérifier.
    * @return true si le nombre est une puissance de 2, sinon false.
    */
    private boolean estPuissanceDeDeux(int nombre) {
        // Vérifier si le nombre est strictement positif
        if (nombre <= 0) {
            return false;
        }

        // Utiliser l'opération de bitwise pour vérifier s'il y a un seul bit positionné à 1
        return (nombre & (nombre - 1)) == 0;
    }

    /**
    * Vérifie si le format d'image est correct.
    *
    * @param magicNumber Le numéro magique du format d'image.
    * @param width       La largeur de l'image.
    * @param height      La hauteur de l'image.
    * @return true si le format est correct, sinon false.
    */
    private boolean isGoodFormat(String magicNumber, int width, int height) {
        // Vérifier si le numéro magique commence par 'P2'
        boolean isMagicNumberValid = magicNumber.startsWith("P2");

        // Vérifier si la largeur et la hauteur sont des puissances de 2 et sont égales
        boolean isWidthPowerOfTwo = estPuissanceDeDeux(width);
        boolean isHeightPowerOfTwo = estPuissanceDeDeux(height);
        boolean areWidthAndHeightEqual = (width == height);

        // Vérifier si toutes les conditions sont remplies
        return isMagicNumberValid && isWidthPowerOfTwo && isHeightPowerOfTwo && areWidthAndHeightEqual;
    }


    /**
     * Lit le contenu d'un fichier et renvoie un tableau 2D d'entiers.
     *
     * @param Path Chemin du fichier à lire.
     * @return Un tableau 2D d'entiers, ou null en cas d'erreur de lecture du fichier.
     */
    public static int[][] readfile(String Path) {
        try {
            // Créer un objet Scanner pour lire le fichier
            Scanner scanner = new Scanner(new File(filePath));

            // Compter le nombre de lignes pour déterminer la taille du tableau
            int rowCount = 0;
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                rowCount++;
            }

            // Réinitialiser le scanner pour lire à partir du début du fichier
            scanner.close();
            scanner = new Scanner(new File(filePath));

            // Initialiser le tableau en fonction du nombre de lignes
            int[][] array = new int[rowCount][];
        
    }
    
}