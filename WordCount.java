import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WordCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("!----------- WORD COUNTER ------------!");
        System.out.println("Enter 'text' to input text, or 'file' to input a file:");
        String inputMethod = scanner.nextLine();

        if (inputMethod.equalsIgnoreCase("text")) {
            System.out.println("Enter your text: ");
            String text = scanner.nextLine();
            countAndDisplayWords(text);
        } else if (inputMethod.equalsIgnoreCase("file")) {
            System.out.println("Enter the file path: ");
            String filePath = scanner.nextLine();

            try {
                String text = readFile(filePath);
                countAndDisplayWords(text);
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
        } else {
            System.out.println("Invalid input method. Please enter 'text' or 'file'.");
        }

        scanner.close();
    }

    // Count and display words
    public static void countAndDisplayWords(String text) {
        String[] words = text.split("[\\s\\p{Punct}]+");
        int wordCount = words.length;
       
        // Create a map to store word frequency
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }
       
        // Display total word count
        System.out.println("Word count: " + wordCount);

        // Display unique word count
        System.out.println("Common word count: " + wordFrequency.size());

        // Display word frequency
        System.out.println("Word Frequency:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    // Read text from a file
    public static String readFile(String filePath) throws FileNotFoundException {
        StringBuilder text = new StringBuilder();
        Scanner fileScanner = new Scanner(new File(filePath));

        while (fileScanner.hasNextLine()) {
            text.append(fileScanner.nextLine()).append(" ");
        }

        fileScanner.close();
        return text.toString();
    }
}
