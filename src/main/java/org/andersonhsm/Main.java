package org.andersonhsm;

import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Department obj1 = new Department.DepartmentBuilder(1, "Books").build();
        Department obj2 = new Department.DepartmentBuilder(2, "Long department name").setAbbreviation("Short").build();

        Seller seller = new Seller(21, "Bob", "bob@gamil.com", new Date(), 3000.0, obj1);

        System.out.println(obj1);
        System.out.println(obj2);
        System.out.println(seller);
    }
}