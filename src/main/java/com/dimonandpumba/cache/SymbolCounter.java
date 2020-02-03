package com.dimonandpumba.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class SymbolCounter {

    private final static int CAPACITY = 5;

    private final static LinkedHashMap<String, CacheEntry> cacheMap = new LinkedHashMap<>();

    LinkedHashMap<Character, Integer>  countingSymbol (String line){
        if (!cacheMap.containsKey(line)) {
            addCacheEntry(line, findUnigueCharacters(line));
        }
        return getCacheEntry(line);
    }

    public void addCacheEntry(String key, LinkedHashMap<Character, Integer> data){
        if(cacheMap.size() >= CAPACITY)
        {
            String entryKeyToBeRemoved = getLFUKey();
            cacheMap.remove(entryKeyToBeRemoved);

            CacheEntry temp = new CacheEntry();
            temp.setCountUniqueSymbol(data);
            temp.setFrequency(0);

            cacheMap.put(key, temp);
        }
        else
        {
            CacheEntry temp = new CacheEntry();
            temp.setCountUniqueSymbol(data);
            temp.setFrequency(0);

            cacheMap.put(key, temp);
        }
    }

    private String getLFUKey(){
        String key = null;
        int minFreq = Integer.MAX_VALUE;

        for(Map.Entry<String, CacheEntry> entry : cacheMap.entrySet())
        {
            if(minFreq > entry.getValue().frequency)
            {
                key = entry.getKey();
                minFreq = entry.getValue().frequency;
            }
        }
        return key;
    }

    public LinkedHashMap<Character,Integer> getCacheEntry(String key){
        if(cacheMap.containsKey(key)){
            CacheEntry temp = cacheMap.get(key);
            temp.frequency++;
            cacheMap.put(key, temp);
            return temp.countUniqueSymbol;
        }
        return null;
    }

    public LinkedHashMap<Character,Integer> findUnigueCharacters (String value){
        char[] phraseInSymbols = value.toCharArray();
        LinkedHashMap<Character, Integer> countUniqueSymbol = new LinkedHashMap<>();

        for (char phraseInSymbol : phraseInSymbols) {
            Integer frequency = countUniqueSymbol.get(phraseInSymbol);
            countUniqueSymbol.put(phraseInSymbol, (frequency == null) ? 1 : ++frequency);
        }

        return countUniqueSymbol;
    }

    static class CacheEntry {

        private LinkedHashMap<Character, Integer> countUniqueSymbol = new LinkedHashMap<>();
        private int frequency;


        public void setCountUniqueSymbol(LinkedHashMap<Character, Integer> countUniqueSymbol) {
            this.countUniqueSymbol = countUniqueSymbol;
        }

        public void setFrequency(int frequency) {
            this.frequency = frequency;
        }
    }

}
