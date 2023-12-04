package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CachingPersonDatabase implements PersonDatabase {
    private final HashMap<Integer, Person> cache;

    private final HashMap<String, HashSet<Integer>> nameIndex;

    private final HashMap<String, HashSet<Integer>> addressIndex;

    private final HashMap<String, HashSet<Integer>> phoneIndex;

    public CachingPersonDatabase() {
        this.cache = new HashMap<>();
        this.nameIndex = new HashMap<>();
        this.addressIndex = new HashMap<>();
        this.phoneIndex = new HashMap<>();
    }

    @Override
    public synchronized void add(Person person) {
        if (cache.containsKey(person.id())) {
            throw new DuplicateIdException("Unique value id isn't unique : " + person.id());
        }

        cache.put(person.id(), person);
        addToIndex(person.name(), nameIndex, person.id());
        addToIndex(person.address(), addressIndex, person.id());
        addToIndex(person.phoneNumber(), phoneIndex, person.id());
    }

    @Override
    public synchronized void delete(int id) {
        Person person = cache.remove(id);
        if (person != null) {
            removeEntryFromIndex(person.name(), nameIndex, id);
            removeEntryFromIndex(person.address(), addressIndex, id);
            removeEntryFromIndex(person.phoneNumber(), phoneIndex, id);
        }
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        HashSet<Integer> ids = nameIndex.getOrDefault(name, new HashSet<>());
        return getPersonsByIds(ids);
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        HashSet<Integer> ids = addressIndex.getOrDefault(address, new HashSet<>());
        return getPersonsByIds(ids);
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        HashSet<Integer> ids = phoneIndex.getOrDefault(phone, new HashSet<>());
        return getPersonsByIds(ids);
    }

    private void addToIndex(String key, HashMap<String, HashSet<Integer>> index, int id) {
        index.computeIfAbsent(key, k -> new HashSet<>()).add(id);
    }

    private void removeEntryFromIndex(String key, HashMap<String, HashSet<Integer>> index, int id) {
        HashSet<Integer> ids = index.get(key);
        if (ids != null) {
            ids.remove(id);
            if (ids.isEmpty()) {
                index.remove(key);
            }
        }
    }

    private List<Person> getPersonsByIds(HashSet<Integer> ids) {
        List<Person> result = new ArrayList<>();
        for (int id : ids) {
            Person person = cache.get(id);
            if (person != null) {
                result.add(person);
            }
        }
        return result;
    }
}
