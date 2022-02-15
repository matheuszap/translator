package system;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import data.Config;

public class Translator {
    private List<Config> machine = new LinkedList<Config>();

    public List<Config> read_machine(String filename) throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader(filename));

        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] info = line.split(" ");

            Config c = new Config(info[0], info[1], info[2], info[3], info[4]);
            machine.add(c);

        }
        return machine;
    }

    public boolean symbol_on_list(Config c, List<String> symbols) {
        boolean is_on_list = false;
        for(int i=0; i<symbols.size(); i++) {
            if (symbols.get(i).equals(c.getCurrent_symbol())) {
                is_on_list = true;
            }
        }
        return is_on_list;
    }

    public List<String> get_symbols(List<Config> m){
        List<String> symbols = new LinkedList<String>();

        for(int i=0; i<m.size(); i++) {
            boolean b = symbol_on_list(m.get(i), symbols);
            if(b == false) {
                symbols.add(m.get(i).getCurrent_symbol());
            }
        }
        return symbols;
    }

    public List<Config> add_initial_end_states(List<Config> machine) {
        List<String> symbols = get_symbols(machine);

        for(int i=0; i<symbols.size(); i++){
            if(!symbols.get(i).equals("_") && !symbols.get(i).equals("&") && !symbols.get(i).equals("#")){
                Config s_begin = new Config("s_begin", symbols.get(i), "*", "l", "s_begin");
                machine.add(s_begin);
            }
        }
        Config s_begin_blank = new Config("s_begin", "_", "&", "r", "s_end");
        machine.add(s_begin_blank);

        for(int i=0; i<symbols.size(); i++){
            if(!symbols.get(i).equals("_") && !symbols.get(i).equals("&") && !symbols.get(i).equals("#")){
                Config s_end = new Config("s_end", symbols.get(i), "*", "r", "s_end");
                machine.add(s_end);
            }
        }
        Config s_end_blank = new Config("s_end", "_", "#", "l", "s_start");
        machine.add(s_end_blank);

        for(int i=0; i<symbols.size(); i++){
            if(!symbols.get(i).equals("_") && !symbols.get(i).equals("&") && !symbols.get(i).equals("#")){
                Config s_start = new Config("s_start", symbols.get(i), "*", "l", "s_start");
                machine.add(s_start);
            }
        }
        Config s_start_begin = new Config("s_start", "&", "*", "r", "0");
        machine.add(s_start_begin);

        return machine;
    }

    public boolean states_on_list(Config c, List<String> states) {
        boolean is_on_list = false;
        for(int i=0; i<states.size(); i++) {
            if (states.get(i).equals(c.getCurrent_state())) {
                is_on_list = true;
            }
        }
        return is_on_list;
    }

    public List<String> get_states(List<Config> m){
        List<String> states = new LinkedList<String>();

        for(int i=0; i<m.size(); i++) {
            boolean b = states_on_list(m.get(i), states);
            if(b == false) {
                states.add(m.get(i).getCurrent_state());
            }
        }
        return states;
    }

    public List<Config> right_limit_states(List<Config> machine){
        List<String> states = get_states(machine);

        for(int i=0; i<states.size(); i++) {
            Config w_end = new Config("w_end_" + i, "_", "#", "l", String.valueOf(i));
            machine.add(w_end);
        }

        for(int i=0; i<states.size(); i++) {
            Config limit_reached = new Config(String.valueOf(i), "#", "_", "r", "w_end_"+i);
            machine.add(limit_reached);
        }

        return machine;
    }

    public List<Config> left_limit_states(List<Config> machine, int original_size) {
        List<String> symbols_1 = get_symbols(machine);
        List<String> symbols_2 = get_symbols(machine);

        for(int i=0; i<symbols_1.size(); i++) { // symbols: 0, 1, x, _
            if (!symbols_1.get(i).equals("_") && !symbols_1.get(i).equals("#")) {
                for (int j = 0; j < symbols_2.size(); j++) {
                    if (!symbols_2.get(j).equals("#")) { // symbols: 0, 1, x, _
                        if (symbols_2.get(j).equals("_")) {
                            Config w = new Config("w" + symbols_1.get(i), symbols_2.get(j), symbols_1.get(i), "r", "wb");
                            machine.add(w);
                        } else {
                            Config w = new Config("w" + symbols_1.get(i), symbols_2.get(j), symbols_1.get(i), "r", "w" + symbols_2.get(j));
                            machine.add(w);
                        }
                    }
                }
            }
        }

        for(int i=0; i<symbols_1.size(); i++) { // symbols: 0, 1, x, _
            if(symbols_1.get(i).equals("_") && !symbols_1.get(i).equals("#")) {
                for (int j = 0; j < symbols_2.size(); j++) {
                    if (!symbols_2.get(j).equals("#")) { // symbols: 0, 1, x, _
                        if (symbols_2.get(j).equals("_")) {
                            Config wb = new Config("wb", symbols_2.get(j), "_", "r", "wb");
                            machine.add(wb);
                        }else{
                            Config wb = new Config("wb", symbols_2.get(j), "_", "r", "w" + symbols_2.get(j));
                            machine.add(wb);
                        }
                    }
                }
            }
        }

        for(int i=0; i<symbols_1.size(); i++){
            if(!symbols_1.get(i).equals("#") && !symbols_1.get(i).equals("_")){
                Config w_end = new Config("w" + symbols_1.get(i), "#", "_", "r", "w_end_" + symbols_1.get(i));
                machine.add(w_end);
            }else if(symbols_1.get(i).equals("#")){
                Config w_end_b = new Config("wb", "#", "*", "l", "return");
                machine.add(w_end_b);
            }
        }

        // w_main
        for(int i=0; i<symbols_1.size(); i++){
            if(!symbols_1.get(i).equals("#") && !symbols_1.get(i).equals("_")){
                Config w_main = new Config("w_main", symbols_1.get(i), "_", "r", "w" + symbols_1.get(i));
                machine.add(w_main);
            }else if(symbols_1.get(i).equals("_")){
                Config w_main = new Config("w_main", symbols_1.get(i), "_", "r", "wb");
                machine.add(w_main);
            }
        }

        // return
        for(int i=0; i<symbols_1.size(); i++){
            if(!symbols_1.get(i).equals("#") && !symbols_1.get(i).equals("_")){
                Config r = new Config("return", symbols_1.get(i), "*", "l", "return");
                machine.add(r);
            }else if(symbols_1.get(i).equals("_")){
                Config r_b = new Config("return", symbols_1.get(i), "*", "r", "3");
                machine.add(r_b);
            }
        }

        List<String> states = get_states(machine);

        for(int i=0; i<states.size(); i++) {
            if(i>original_size) return machine;
            Config limit_reached = new Config(String.valueOf(i), "&", "*", "r", "w_main");
            machine.add(limit_reached);
        }

        return machine;
    }

    public int original_states(List<Config> machine){
        int size = get_states(machine).size();
        return size - 1;
    }

    public void write_file(List<Config> machine){
        try {
            FileWriter w = new FileWriter("saida.out");
            for(int i=0; i<machine.size(); i++) {
                w.write(machine.get(i).getCurrent_state() + " " + machine.get(i).getCurrent_symbol() + " " + machine.get(i).getNew_symbol() + " " + machine.get(i).getDirection() + " " + machine.get(i).getNew_state() + "\n");
            }
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
