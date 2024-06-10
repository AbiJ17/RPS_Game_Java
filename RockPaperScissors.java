import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * Plays Rock Paper Scissors against one player.
 */
public class RockPaperScissors {

    public static final int ROCK = 1;
    public static final int PAPER = 2;
    public static final int SCISSORS = 3;

    private static Random random;

    public RockPaperScissors(String apiKey) {
        random = new Random();
    }

    public static int getComputerThrow() {
        // Generate a random throw using Random.org API
        try {
            URL url = new URL("https://www.random.org/integers/?num=1&min=1&max=3&col=1&base=10&format=plain&rnd=new&apiKey=3cfd4879-b1a6-4776-aa9e-b76941e6cbab");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = reader.readLine();
            int computerThrow = Integer.parseInt(response.trim());

            reader.close();
            return computerThrow;
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while fetching data from RANDOM.org API: " + e.getMessage());
        }
    }

    public static String getThrowName(int throwValue) {
        switch (throwValue) {
            case ROCK:
                return "Rock";
            case PAPER:
                return "Paper";
            case SCISSORS:
                return "Scissors";
            default:
                return "UNKNOWN";
        }
    }

    public static String getResult(int playerThrow, int computerThrow) {
        if (playerThrow == computerThrow) {
            return "It's a draw!";
        } else if ((playerThrow == ROCK && computerThrow == SCISSORS) ||
                   (playerThrow == PAPER && computerThrow == ROCK) ||
                   (playerThrow == SCISSORS && computerThrow == PAPER)) {
            return "Player wins!";
        } else {
            return "Computer wins!";
        }
    }
}


		




