package game;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {


    public static final SecureRandomG computer = new SecureRandomG();
    public static final Hmac hm256 = new Hmac();
    public static final Table table_ = new Table();
    public static int compaaa = 0;


    public static int menu(String[] args) {

        int return_ind = 0;
        System.out.println("Available moves:");

        for (int i = 0; i < args.length; i++) {
            System.out.println(i + 1 + " - " + args[i]);
        }

        System.out.println("0 - exit\n" + "? - help\n" + "Enter your move:");

        Scanner some_name = new Scanner(System.in);
        String word = some_name.nextLine();

        if (word.contains("?") && word.length() == 1) {
            System.out.println(table(args));
            menu(args);
        } else {
            try {
                int generate = Integer.parseInt(word);
                return_ind = my_index(generate, args);
            } catch (NumberFormatException e) {
                System.out.println("");
                menu(args);
            }
        }
        return return_ind;
    }


    public static int my_index(int ind, String[] args) {
        int ch = 0;
        if (ind >= 0 && ind <= args.length) {
            if (ind == 0) {
                System.exit(0);
            } else {
                int index = ind - 1;
                ch  = index;
                String my_move = args[index];
                System.out.println("Your move:" + my_move);
            }
        } else {
            menu(args);
        }
        return ch;
    }


    private static int computer_choise(String[] args){

        int player2_comp = computer.random_index(args.length);
        compaaa = player2_comp;
        return player2_comp;
    }


    public static int winner(int player1_myself, int player2_comp, String[] args){

        if (player1_myself == player2_comp) {
            return 0;
        }else {
            int r = (player2_comp - player1_myself + args.length) % args.length;
            return r > args.length/2 ? 1 : -1;
        }
    }


    public static String result(int res){
        String k = "";
        if (res == 0) {
            k = "Draw";
        } else if (res == -1) {
            k = "You win!";
        } else if (res == 1) {
            k = "You lose!";
        }
        return k;
    }


    private static String table(String[] arg){
        return table_.tables(arg);
    }

    private static String hmac(String[] args){
        String hmacсс = hm256.hmac256(args[compaaa]);
        return hmacсс;
    }

    private static String hmac_key() {
        String computer_choise = hm256.key;
        return "HMAC key: " + computer_choise;
    }


    public static void main(String[] args){

        Set<String> mySet = new HashSet<String>(Arrays.asList(args));
        String[] name = mySet.toArray(new String[mySet.size()]);

        if (args.length == 0) {
            System.out.println("Arguments must be passed");
            System.exit(0);
        } else if (mySet.size() < args.length) {
            System.out.println("Must be: " + Arrays.toString(name));
            System.exit(0);
        }
        else if (args.length % 2 != 1) {
            System.out.println("There must be an odd numbered");
            System.exit(0);
        }else if (args.length == 1) {
            System.out.println("There must be more than 1");
            System.exit(0);
        }

        int comp = computer_choise(args);
        System.out.println(hmac(args));
        int myind = menu(args);
        int winne = winner(myind, compaaa, args);
        String resul = result(winne);
        while (resul=="Draw"){
            resul = result(winne);
        }
        System.out.println("Computer move:" + args[comp]);
        System.out.println(resul);
        System.out.println(hmac_key());
    }
}