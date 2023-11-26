package org.andersonhsm;

import model.dao.DaoFactory;
import model.dao.SellerDao;

public class Main {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        sellerDao.findById(3);
    }
}