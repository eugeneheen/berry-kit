package com.github.eugeneheen.berry.kit.test.core;

import com.github.eugeneheen.berry.kit.core.JsonKit;
import com.github.eugeneheen.berry.kit.exception.JsonParseException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonKitTest {

    private static JsonKit jsonKit;

    @BeforeClass
    public static void beforeClass() {
        jsonKit = new JsonKit();
    }

    @Test
    public void testReadStrinObject() {
        String json = "{\"name\":\"Eugene\", \"age\":18}";
        try {
            User user = jsonKit.read(json, User.class);
            Assert.assertNotNull(user);
        } catch (JsonParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadInputstreamObject() {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("json.txt");
        try {
            User user = jsonKit.read(inputStream, User.class);
            Assert.assertNotNull(user);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testReadStringAsMap() {
        String json = "{\"name\":\"Eugene\", \"age\":18}";
        try {
            Map<String, Object> map = jsonKit.readAsMap(json);
            Assert.assertNotNull(map);
            Assert.assertEquals(2, map.size());
        } catch (JsonParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadInputstreamAsMap() {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("json.txt");
        try {
            Map<String, Object> map = jsonKit.readAsMap(inputStream);
            Assert.assertNotNull(map);
            Assert.assertEquals(2, map.size());
        } catch (JsonParseException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testReadStringAsCollection(){
        String json = "[{\"name\":\"Eugene\", \"age\":18},{\"name\":\"King\", \"age\":20},{\"name\":\"Susan\", \"age\":60}]";
        try {
            List<User> list = jsonKit.readAsCollection(json, List.class, User.class);
            Assert.assertNotNull(list);
            Assert.assertNull(list.get(0).getDesc());
            Assert.assertEquals(3, list.size());
        } catch (JsonParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadCollectionObjectAsCollection(){
        List<User> users = new ArrayList<>();
        User eugene = new User();
        eugene.setName("Eugene");
        eugene.setAge(20);

        User king = new User();
        king.setName("King");
        king.setAge(22);

        users.add(eugene);
        users.add(king);

        try {
            List<User> list = jsonKit.readAsCollection(users, List.class, User.class);
            Assert.assertNotNull(list);
            Assert.assertNull(list.get(0).getDesc());
            Assert.assertNotNull(list.get(0).getName());
            Assert.assertEquals(2, list.size());
        } catch (JsonParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadinputStreamAsCollection(){
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("users.txt");
        try {
            List<User> list = jsonKit.readAsCollection(inputStream, List.class, User.class);
            Assert.assertNotNull(list);
            Assert.assertEquals(3, list.size());
        } catch (JsonParseException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testWriteMap() {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("name", "eugene");
        userMap.put("age", 18);
        userMap.put("desc", "");

        String expected = "{\"name\":\"eugene\",\"age\":18,\"desc\":\"\"}";
        try {
            String json = jsonKit.write(userMap);
            System.out.println(json);
            Assert.assertEquals(expected, json);
        } catch (JsonParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWriteObject() {
        User user = new User();
        user.setName("Eugene");
        user.setAge(20);
//        user.setDesc("Junit测试描述......");

        String expected = "{\"name\":\"Eugene\",\"age\":20}";
        try {
            String json = jsonKit.write(user);
            Assert.assertEquals(expected, json);
        } catch (JsonParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWriteList() {
        List<User> users = new ArrayList<>();
        User eugene = new User();
        eugene.setName("Eugene");
        eugene.setAge(20);

        User king = new User();
        king.setName("King");
        king.setAge(22);

        users.add(eugene);
        users.add(king);

        String expected = "[{\"name\":\"Eugene\",\"age\":20},{\"name\":\"King\",\"age\":22}]";
        try {
            String json = jsonKit.write(users);
            Assert.assertEquals(expected, json);
        } catch (JsonParseException e) {
            e.printStackTrace();
        }
    }
}
