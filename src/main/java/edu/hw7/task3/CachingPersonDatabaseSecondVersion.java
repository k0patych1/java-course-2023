package edu.hw7.task3;

import javax.naming.directory.InvalidAttributeIdentifierException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CachingPersonDatabaseSecondVersion implements PersonDatabase {
    private final HashMap<Integer, Person> cache;
    private final HashMap<String, HashSet<Integer>> nameIndex;
    private final HashMap<String, HashSet<Integer>> addressIndex;
    private final HashMap<String, HashSet<Integer>> phoneIndex;
    private final ReadWriteLock lock;

    public CachingPersonDatabaseSecondVersion() {
        this.cache = new HashMap<>();
        this.nameIndex = new HashMap<>();
        this.addressIndex = new HashMap<>();
        this.phoneIndex = new HashMap<>();
        this.lock = new ReentrantReadWriteLock();
    }

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            if (cache.containsKey(person.id())) {
                throw new DuplicateIdException("Unique value id isn't unique : " + person.id());
            }

            cache.put(person.id(), person);
            addToIndex(person.name(), nameIndex, person.id());
            addToIndex(person.address(), addressIndex, person.id());
            addToIndex(person.phoneNumber(), phoneIndex, person.id());
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            Person person = cache.remove(id);
            if (person != null) {
                removeEntryFromIndex(person.name(), nameIndex, id);
                removeEntryFromIndex(person.address(), addressIndex, id);
                removeEntryFromIndex(person.phoneNumber(), phoneIndex, id);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            HashSet<Integer> ids = nameIndex.getOrDefault(name, new HashSet<>());
            return getPersonsByIds(ids);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            HashSet<Integer> ids = addressIndex.getOrDefault(address, new HashSet<>());
            return getPersonsByIds(ids);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            HashSet<Integer> ids = phoneIndex.getOrDefault(phone, new HashSet<>());
            return getPersonsByIds(ids);
        } finally {
            lock.readLock().unlock();
        }
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
