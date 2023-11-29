import java.io.FileNotFoundException;
import java.util.Arrays;


public class Test {

    public static void main(String[] args) {
        // Replace "path/to/your/file.txt" with the actual file path you want to test
        String filePath = "test.txt";

        // Test the readfile method
        testReadFile(filePath);
    }

    private static void testReadFile(String filePath) {
        int[][] result = Util.readFile(filePath);

        if (result != null) {
            System.out.println("File successfully read. Content:");

            for (int[] row : result) {
                System.out.println(Arrays.toString(row));
            }
        } else {
            System.out.println("Error reading the file.");
        }
    }
}
