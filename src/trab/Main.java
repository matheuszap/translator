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
        List<Config> machine = new LinkedList<Config>();

        String filename = "/home/zap/Downloads/sameamount10.in";
        machine = t.read_machine(filename);

        machine = t.right_limit_states(machine);
        machine = t.add_initial_end_states(machine);

        for(int i=0; i<machine.size(); i++) {
            System.out.println(machine.get(i).toString());
        }



    }
}
