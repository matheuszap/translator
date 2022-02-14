package trab;


import data.Config;
import system.Translator;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Translator t = new Translator();
        List<Config> configurations = new LinkedList<Config>();

        String filename = "/home/zap/Downloads/odd.in";

        configurations = t.read_machine(filename);

        for(int i=0; i<configurations.size(); i++) {
            System.out.println(configurations.get(i).toString());
        }
    }


}
