import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.*;


/*
 * Elven Shum
 * Animal Guessing Game
 * Run Main to begin
 */

public class Animal<T> {
    //    private static AnimalST<String> root = null;
    private static AnimalST game = new AnimalST();
    private static AnimalST.STNode node = game.root;
    private static BufferedReader consoleRead = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        init();
        playGame();
        while(playAgain()){
            playGame();
        }
        saveMemory();
    }

    public static void init() throws IOException {
        StdOut.println("Welcome to Elven's Animal Guessing Game");
        StdOut.println("Would you like to load previous game memory? Enter: Yes/No");
        String command = StdIn.readString();
        if (command.equalsIgnoreCase("yes")) {
            game.init();
            recallMemory();
        } else {
            reset();
        }
    }

    public static void recallMemory() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("memory.txt"));
        recallMemory(br, game.root);
        br.close();
    }

    public static void recallMemory(BufferedReader br, AnimalST.STNode node) throws IOException {
        String memLine = br.readLine();
        if (memLine == null) {
            StdOut.println("Memory Loaded!");
            return;
        } else if (memLine.charAt(0) == 'a') {
            node.element = memLine.substring(2);
            return;
        }
        //else line is a question and continues with pre order traversal
        node.element = memLine.substring(2);
        StdOut.println(node.element + "oi");
        node.left = new AnimalST.STNode("");
        recallMemory(br, node.left);

        node.right = new AnimalST.STNode("");
        recallMemory(br, node.right);

    }

    public static void saveMemory() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("memory.txt"));
        saveMemory(bw, game.root);
        bw.close();
    }

    private static void saveMemory(BufferedWriter bw, AnimalST.STNode node) throws IOException {
        //checks if node is empty
        if (node == null || node.element.equals("")) {
            return;
        }
        if (!(node.element.contains("q:") || node.element.contains("a:"))) {
            if (node.right == null || node.left == null) {
                bw.write("a:" + node.element);
            } else {
                bw.write("q:" + node.element);
            }
        } else {
            bw.write(node.element);
        }
        bw.newLine();
        saveMemory(bw, node.left);
        saveMemory(bw, node.right);
    }

    public static void playGame() throws IOException {
        game.init(); //curr == root;
        //traverses thru the SearchTree
        while (!game.isLeaf()) {
            //outputs Question
            StdOut.print("\nQuestion: ");
            StdOut.println(game.curr.element);

            StdOut.println("Enter answer (yes/no): ");
            String command = StdIn.readString();

            if (command.equalsIgnoreCase("yes")) {
                game.travLeft();
            } else {
                game.travRight();
            }
        }
        //once finished, begins guessing
        String prevGuess = game.curr.element;
        StdOut.println("is it a(n) " + prevGuess + " ?");

        String command = StdIn.readString();
        if (command.equalsIgnoreCase("yes")) {
            StdOut.println("I win!");
        } else {
            StdOut.println("Dang it. What was the answer?");
            String newAns = consoleRead.readLine();

            StdOut.println("Give me question that distinguishes " + prevGuess + " from " + newAns);
            String newQues = consoleRead.readLine();

            StdOut.println("Is \"" + newAns + "\" Yes or No for " + "\"" + newQues + "\"");
            command = consoleRead.readLine();
            if (command.equalsIgnoreCase("yes")) {
                game.curr.element = newQues;
                game.addLeft(newAns);
                game.addRight(prevGuess);
            } else {
                game.curr.element = newQues;
                game.addLeft(prevGuess);
                game.addRight(newAns);
            }
        }
        StdOut.println("Got it!");
    }

    public static boolean playAgain() throws IOException {
        StdOut.println("Would you like to play again?");
        String command = StdIn.readString();
        if (command.equalsIgnoreCase("yes")) {
            StdOut.println("Great! I love learning!");
            return true;
        } else {
            StdOut.println("See you next time!");
            return false;
        }
    }



    public static void reset() throws IOException {
        StdOut.print("Enter a question: ");
        //writes new Question as First Node data
        String initQ = consoleRead.readLine();
        game = new AnimalST(initQ);
        game.init();
        //requires addition of first nodes
        StdOut.println("Ok, Enter something that's True for this question");
        game.addLeft(consoleRead.readLine());
        StdOut.println("Now, Enter something that's False for this question");
        game.addRight(consoleRead.readLine());
    }
}