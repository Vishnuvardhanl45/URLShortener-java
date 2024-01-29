import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class URLShortener
{
    private Map<String, String> shortToLongMap;
    private Map<String, String> longToShortMap;
    public URLShortener() 
    {
        shortToLongMap = new HashMap<>();
        longToShortMap = new HashMap<>();
    }
    public String shortenURL(String longURL)
    {
        if(longToShortMap.containsKey(longURL))
        {
            return longToShortMap.get(longURL);
        }
        else 
        {
            String shortURL = generateShortURL(longURL);
            shortToLongMap.put(shortURL, longURL);
            longToShortMap.put(longURL, shortURL);
            return shortURL;
        }
    }

    public String expandURL(String shortURL)
    {
        return shortToLongMap.getOrDefault(shortURL, "Short URL not found.");
    }

    private String generateShortURL(String longURL)
    {
        return "http://short.url/" + longURL.hashCode();
    }

    public static void main(String[] args)
    {
        URLShortener urlShortener = new URLShortener();
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println("1. Shorten URL");
            System.out.println("2. Expand URL");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            while (!scanner.hasNextInt())
            {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice)
            {
                case 1:
                    System.out.print("Enter the long URL to shorten: ");
                    String longURL = scanner.nextLine();
                    String shortURL = urlShortener.shortenURL(longURL);
                    System.out.println("Shortened URL: " + shortURL);
                    break;
                case 2:
                    System.out.print("Enter the short URL to expand: ");
                    String shortURLToExpand = scanner.nextLine();
                    String expandedURL = urlShortener.expandURL(shortURLToExpand);
                    System.out.println("Expanded URL: " + expandedURL);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
