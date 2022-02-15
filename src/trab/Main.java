package trab;


import data.Config;
import system.Translator;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Translator t = new Translator();
        List<Config> machine = new LinkedList<Config>();

        String filename = "/home/zap/IdeaProjects/trab_tec/files/odd.in";
        machine = t.read_machine(filename);
        int size = t.original_states(machine);
        machine = t.right_limit_states(machine);
        machine = t.left_limit_states(machine, size);
        machine = t.add_initial_end_states(machine);
        t.write_file(machine);

        for(int i=0; i<machine.size(); i++) {
            System.out.println(machine.get(i).toString());
        }

    }
}
