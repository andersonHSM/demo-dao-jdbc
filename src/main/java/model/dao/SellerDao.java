package model.dao;

import model.entities.Seller;

import java.util.List;

public interface SellerDao extends BaseDao<Seller> {

    List<Seller> findByDepartment(Integer departmentId);
}
