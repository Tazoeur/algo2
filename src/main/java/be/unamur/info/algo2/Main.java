package be.unamur.info.algo2;

import be.unamur.info.algo2.Problem1.Problem1;
import be.unamur.info.algo2.Problem2.Problem2;

public class Main {

    public static void main(String[] args) {

        String resources_directory = System.getProperty("user.dir") + "/src/test/resources";
        problem_1(resources_directory + "/problem1/DiviserPourRegner_2.2.txt");
        problem_2(resources_directory + "/problem2/ProgrammationDynamique_3.2.txt");
    }

    public static String[] problem_1_naive(String s_file) {
        System.out.println("===");
        System.out.println(s_file);
        System.out.println("===");
        String[] s_tmp = {"Goku", "Piccolo", null, "Gohan"};
        //String[] s_tmp = null;
        return s_tmp;
    }


    public static String[] problem_1(String s_file) {
        Problem1 p1 = new Problem1(s_file);
        String[] result = p1.solve();

        return result;
    }

    public static String[] problem_2_naive(String s_file) {
        System.out.println("====");
        System.out.println(s_file);
        System.out.println("====");
        String[] s_tmp = {"8", "1", "3", "0"};
        //String[] s_tmp = null;
        return s_tmp;
    }

    public static String[] problem_2(String s_file) {
        Problem2 p2 = new Problem2(s_file);
        String[] result = p2.solve();
        return result;
    }

    public static String[] problem_3_naive(String s_file) {
        System.out.println("===");
        System.out.println(s_file);
        System.out.println("===");
        String[] s_tmp = {"1", "0", "1", "1"};
        //String[] s_tmp = null;
        return s_tmp;
    }


    public static String[] problem_3(String s_file) {
        System.out.println("===");
        System.out.println(s_file);
        System.out.println("===");
        String[] s_tmp = {"1", "0", "1", "1"};
        //String[] s_tmp = null;
        return s_tmp;
    }


}
