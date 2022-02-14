package data;

public class Config {
    private String current_state;
    private String current_symbol;
    private String new_symbol;
    private String direction;
    private String new_state;

    public Config(){

    }

    public Config(String current_state, String current_symbol, String new_symbol, String direction, String new_state) {
        this.current_state = current_state;
        this.current_symbol = current_symbol;
        this.new_symbol = new_symbol;
        this.direction = direction;
        this.new_state = new_state;
    }

    public String getCurrent_state() {
        return current_state;
    }

    public void setCurrent_state(String current_state) {
        this.current_state = current_state;
    }

    public String getCurrent_symbol() {
        return current_symbol;
    }

    public void setCurrent_symbol(String current_symbol) {
        this.current_symbol = current_symbol;
    }

    public String getNew_symbol() {
        return new_symbol;
    }

    public void setNew_symbol(String new_symbol) {
        this.new_symbol = new_symbol;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getNew_state() {
        return new_state;
    }

    public void setNew_state(String new_state) {
        this.new_state = new_state;
    }

    @Override
    public String toString() {
        return current_state + " " + current_symbol + " " + new_symbol + " " + direction + " " + new_state;
    }
}
