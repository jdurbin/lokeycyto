#!/usr/bin/env groovy 

import durbin.util.*;
import durbin.charts.*;
import javax.swing.*;
import java.awt.*;

class MultidimensionalMap2 extends LinkedHashMap {
    @Override
    public Object get(Object key) {
        //println "key = $key"
        if (!containsKey(key)) {
            put(key, new MultidimensionalMap2())
        }
        return super.get(key)
    }
    
    public boolean contains(key1,key2){      
      if (!keySet().contains(key1)) return(false);
      else{
        secondMap = this[key1]
        if (!secondMap.keySet().contains(key2)) return(false);
        else return(true);
      }      
    }   
}


def data = new MultidimensionalMap2()

data['a']['b'] = 5.0
data['a']['c'] = 5.0
data['a']['d'] = 5.0

println data.keySet()

println data.contains('a','b')
println data.contains('a','c')
println data.contains('a','d')
println data.contains('a','e')
println data.contains('a','b')
println data.contains('b','b')


/*
println "A $m \t "+m.keySet()

m.numCells.blank[10] = 0

println "B $m \t "+m.keySet()

m.numCells.blank[5] = 1

println "C $m \t "+m.keySet()

println "D ${m.numCells}\t"+m.numCells.keySet()
println "E ${m.numCells.blank}\t"+m.numCells.blank.keySet()

println  "contains 5: "+m.numCells.blank.keySet().contains(5)
println  "contains 6: "+m.numCells.blank.keySet().contains(6)

println  "contains 5: "+m.numCells.blank.containsKey(5)
println  "contains 6: "+m.numCells.blank.containsKey(6)
*/

/*
println data['numCells']['blank']['10'].keySet()

data['numCells']['blank']['10'][1] = 2.5
data['numCells']['blank']['20'][1] = 3.5
data['numCells']['blank']['30'][1] = 4.5
data['numCells']['blank']['40'][1] = 4.5

//println data['numCells']['blank']['10']
println data['numCells']['blank']['10'].keySet()
*/
