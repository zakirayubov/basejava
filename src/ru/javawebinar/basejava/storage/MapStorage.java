package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(Resume r, Object index) {

    }

    @Override
    protected boolean isExist(Object index) {
        return false;
    }

    @Override
    protected void doSave(Resume r, Object index) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Object index) {
        return map.get(index);
    }

    @Override
    protected void doDelete(Object index) {
        map.remove(index);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return null;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return new ArrayList<Resume>();
    }

    @Override
    public int size() {
        return map.size();
    }
}
