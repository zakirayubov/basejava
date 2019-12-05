package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.assertEquals;
import static ru.javawebinar.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;


public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4);





    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume updated = new Resume(UUID_1);
        storage.update(updated);
        assertEquals(new Resume[] {updated, RESUME_2, RESUME_3}, storage.getAll());

    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        Resume updated = new Resume("uuid7");
        storage.update(updated);
    }

    @Test
    public void getAll() {
        assertEquals(new Resume[] {RESUME_1, RESUME_2, RESUME_3}, storage.getAll());
    }

    @Test(expected = ExistStorageException.class)
    public void save() {
        storage.save(RESUME_1);
    }

    @Test
    public void saveExist() {
        storage.save(RESUME_4);
        assertEquals(4, storage.size());
        assertEquals(new Resume[] {RESUME_1, RESUME_2, RESUME_3, RESUME_4}, storage.getAll());
    }

    @Test(expected = StorageException.class)
    public void saveOverflow()  {
        try {
            for (int i = 4; i < STORAGE_LIMIT+1; i++) {
                storage.save(new Resume());
            }
        } catch (Exception e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void get() {
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }


}