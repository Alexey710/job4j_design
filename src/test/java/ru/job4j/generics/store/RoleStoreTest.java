package ru.job4j.generics.store;

import org.junit.Assert;
import org.junit.Test;

public class RoleStoreTest {

    @Test
    public void add() {
        RoleStore roleStore = new RoleStore();
        Role role002 = new Role("002");
        roleStore.add(new Role("001"));
        roleStore.add(role002);
        roleStore.add(new Role("003"));
        Role output = roleStore.findById("002");
        Assert.assertEquals(output, role002);
    }

    @Test
    public void replace() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("001"));
        Role role002 = new Role("002");
        roleStore.replace("001", role002);
        Role output = roleStore.findById("001");
        Assert.assertNull(output);
    }

    @Test
    public void delete() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("001"));
        roleStore.delete("001");
        Role output = roleStore.findById("001");
        Assert.assertNull(output);
    }

    @Test
    public void findById() {
        RoleStore roleStore = new RoleStore();
        Role role002 = new Role("002");
        roleStore.add(new Role("001"));
        roleStore.add(role002);
        roleStore.add(new Role("003"));
        Role output = roleStore.findById("002");
        Assert.assertEquals(output, role002);
    }

    @Test
    public void findByInvalidId() {
        RoleStore roleStore = new RoleStore();
        Role output = roleStore.findById("000001");
        Assert.assertNull(output);
    }
}