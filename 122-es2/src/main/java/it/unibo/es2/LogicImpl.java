package it.unibo.es2;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LogicImpl implements Logic{

    final Map<Pair<Integer, Integer>, Boolean> map = new HashMap<>();

    public LogicImpl(int size) {
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                map.put(new Pair<>(i, j), false);
            }
        }
    }

    @Override
    public boolean fullRow() {
        return this.map.keySet().stream()
        .collect(Collectors.groupingBy(Pair::getX)) // Raggruppa le celle per riga
        .values().stream() // Ottieni i gruppi di celle (una lista per riga)
        .anyMatch(cells -> cells.stream().allMatch(pair -> map.getOrDefault(pair, false)));
        
    }

    @Override
    public boolean fullColumn() {
        return this.map.keySet().stream()
        .collect(Collectors.groupingBy(Pair::getY)) // Raggruppa le celle per riga
        .values().stream() // Ottieni i gruppi di celle (una lista per riga)
        .anyMatch(cells -> cells.stream().allMatch(pair -> map.getOrDefault(pair, false)));
    }

    @Override
    public Boolean hit(int x, int y) {
        boolean val = map.get(new Pair<>(x, y));
        map.put(new Pair<>(x, y), !val);
        return val;
    }

}
