package org.andersonhsm;

import model.entities.Department;

public class Main {
    public static void main(String[] args) {
        Department obj1 = new Department.DepartmentBuilder(1, "Books").build();
        Department obj2 = new Department.DepartmentBuilder(2, "Long department name").setAbbreviation("Short").build();

        System.out.println(obj1);
        System.out.println(obj2);
    }
}