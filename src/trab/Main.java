package trab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //Config c = new Config("0", "_", "_", "l", "1");
        List<Config> configurations = new LinkedList<Config>();

        //System.out.println(c.getCurrent_state() + " " + c.getCurrent_symbol() + " " + c.getNew_symbol()
        //      + " " + c.getDirection() + " " + c.getNew_state());

        Scanner in = new Scanner(new FileReader("/home/zap/Downloads/sameamount10.in"));

        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] info = line.split(" ");

            Config c = new Config(info[0], info[1], info[2], info[3], info[4]);
            configurations.add(c);
        }

        //for(int i=0; i<configurations.size(); i++) {
        //    System.out.println(configurations.get(i).toString());
        //}

        System.out.println(configurations.get(1).getCurrent_state());
        System.out.println(configurations.get(1).getCurrent_symbol());
        System.out.println(configurations.get(1).getNew_symbol());
        System.out.println(configurations.get(1).getDirection());
        System.out.println(configurations.get(1).getNew_state());





    }
}
