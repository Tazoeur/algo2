package be.unamur.info.algo2;

import be.unamur.info.algo2.Problem1.Problem1;
import be.unamur.info.algo2.Problem2.Problem2;
import be.unamur.info.algo2.Reader.Reader;

public class Main {

    public static void main(String[] args) {

        String resources_directory = System.getProperty("user.dir") + "/src/test/resources";
        String resource_p1 = new Reader(resources_directory + "/problem1/DiviserPourRegner_2.2.txt").getContent();
        String resource_p2 = new Reader(resources_directory + "/problem2/ProgrammationDynamique_3.2.txt").getContent();

        problem_1_naive(resource_p1);
        problem_1(resource_p1);
        problem_2_naive(resource_p2);
        problem_2(resource_p2);
    }

    public static String[] problem_1_naive(String s_file) {
        Problem1 p1 = new Problem1(s_file);
        String[] result = p1.solveNaive();
        return result;
    }


    public static String[] problem_1(String s_file) {
        Problem1 p1 = new Problem1(s_file);
        String[] result = p1.solve();

        return result;
    }

    public static String[] problem_2_naive(String s_file) {
        Problem2 p2 = new Problem2(s_file);
        String[] result = p2.solveNaive();
        return result;
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
