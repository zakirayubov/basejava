package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializer.JsonStreamSerializer;

public class JsonStreamSerializerTest extends AbstractStorageTest {
    public JsonStreamSerializerTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamSerializer()));
    }
}