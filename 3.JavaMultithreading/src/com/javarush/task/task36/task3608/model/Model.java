package com.javarush.task.task36.task3608.model;

/**
 * Created by Admin on 11.02.2017.
 */
public interface Model {
    ModelData getModelData();
    void loadDeletedUsers();
    void loadUserById(long userId);
    void deleteUserById(long id);
    void loadUsers();
}
