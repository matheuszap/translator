package system;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import data.Config;

public class Translator {
    private List<Config> configurations = new LinkedList<Config>();

    public List<Config> read_machine(String filename) throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader(filename));

        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] info = line.split(" ");

            Config c = new Config(info[0], info[1], info[2], info[3], info[4]);
            configurations.add(c);
        }
        return configurations;
    }

}
