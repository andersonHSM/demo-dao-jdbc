package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {
    private Connection connection;

    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    private static Seller getSellerFromResultSet(ResultSet resultSet, Department department) throws SQLException {
        Seller seller = new Seller(
                resultSet.getInt("Id"),
                resultSet.getString("Name"),
                resultSet.getString("Email"),
                resultSet.getDate("BirthDate"),
                resultSet.getDouble("BaseSalary"),
                department
        );
        return seller;
    }

    private static Department getDepartmentFromResultSet(ResultSet resultSet) throws SQLException {
        Department department = new Department.DepartmentBuilder(
                resultSet.getInt("DepartmentId"),
                resultSet.getString("DepName")
        ).build();
        return department;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT seller.*, department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "WHERE seller.Id = ?");

            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Department department = getDepartmentFromResultSet(resultSet);

                Seller seller = getSellerFromResultSet(resultSet, department);

                return seller;
            }

            return null;


        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(statement);
        }

    }

    @Override
    public List<Seller> findAll() {
        return null;
    }

    @Override
    public List<Seller> findByDepartment(Integer departmentId) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT seller.*,department.Name as DepName FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id WHERE DepartmentId = ? " +
                    "ORDER BY Name");

            statement.setInt(1, departmentId);

            resultSet = statement.executeQuery();

            Department department = null;
            List<Seller> sellersList = new ArrayList<>();

            while (resultSet.next()) {
                if (department == null) {
                    department = getDepartmentFromResultSet(resultSet);
                }

                Seller seller = getSellerFromResultSet(resultSet, department);
                sellersList.add(seller);
            }

            return sellersList;


        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(statement);
        }
    }
}
