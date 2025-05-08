package model;

// handle memory functions, add(currently hidden), store, recall, clear
public class MemoryStorage {
    private double memoryValue;

    public MemoryStorage() {
        memoryValue = 0.0;
    }

//    public void PrintMr(){
//        System.out.println("Button MR clicked");
//    }

    public void store(double value) {
        memoryValue = value;
    }

    public String recall() {
        return String.valueOf(memoryValue);
    }

    public void clear() {
        memoryValue = 0.0;
    }

    public void add(double value) {
        memoryValue += value;
    }
}