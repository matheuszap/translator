package system;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
            if(!symbols.get(i).equals("_")){
                Config s_begin = new Config("s_begin", symbols.get(i), "*", "l", "s_begin");
                machine.add(s_begin);
            }
        }
        Config s_begin_blank = new Config("s_begin", "_", "&", "r", "s_end");
        machine.add(s_begin_blank);

        for(int i=0; i<symbols.size(); i++){
            if(!symbols.get(i).equals("_")){
                Config s_end = new Config("s_end", symbols.get(i), "*", "r", "s_end");
                machine.add(s_end);
            }
        }
        Config s_end_blank = new Config("s_end", "_", "#", "l", "s_start");
        machine.add(s_end_blank);

        for(int i=0; i<symbols.size(); i++){
            if(!symbols.get(i).equals("_")){
                Config s_start = new Config("s_start", symbols.get(i), "*", "l", "s_start");
                machine.add(s_start);
            }
        }
        Config s_start_begin = new Config("s_start", "&", "*", "r", "0");
        machine.add(s_start_begin);

        return machine;
    }


}
