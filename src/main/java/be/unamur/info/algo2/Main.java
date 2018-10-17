package be.unamur.info.algo2;

import be.unamur.info.algo2.Problem1.Problem1;
import be.unamur.info.algo2.Problem1.Sequence;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String resources_directory = System.getProperty("user.dir") + "/src/test/resources";
        problem_1(resources_directory + "/problem1/DiviserPourRegner_2.2.txt");
    }

    public static String[] problem_1(String s_file) {
        Problem1 p1 = new Problem1();
        p1.readFile(s_file);
        String[] result = p1.solve();

        return result;
    }

    public static String[] problem_2(String s_file) {
        System.out.println("====");
        System.out.println(s_file);
        System.out.println("====");
        String[] s_tmp = {"8", "1", "3", "0"};
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
