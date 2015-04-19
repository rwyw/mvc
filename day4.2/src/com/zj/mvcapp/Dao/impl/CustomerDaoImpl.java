package com.zj.mvcapp.Dao.impl;

import java.util.List;

import com.zj.mvcapp.Dao.CriteriaCustomer;
import com.zj.mvcapp.Dao.CustomerDao;
import com.zj.mvcapp.Dao.DAO;
import com.zj.mvcapp.domain.Customer;

public class CustomerDaoImpl extends DAO<Customer> implements CustomerDao{
	
	public CustomerDaoImpl() {
	}

	@Override
	public List<Customer> getAll() {
		String sql = "SELECT id, name, age, address, phone FROM customers";
		return getForList(sql);
	}

	@Override
	public void save(Customer customer) {
		String sql = "INSERT INTO customers(id, name, age, address, phone) VALUES (?,?,?,?,?)";
		update(sql, customer.getId(), customer.getName(), customer.getAge(), 
				customer.getAddress(), customer.getPhone());
	}

	@Override
	public Customer get(String id) {
		String sql = "SELECT id, name, age, address, phone FROM customers WHERE id = ?";
		return get(sql, id);
	}

	@Override
	public void delete(String id) {
		String sql = "DELETE FROM customers WHERE id = ?";
		update(sql, id);
	}

	@Override
	public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc) {
		String sql = "SELECT id, name, age, address, phone FROM customers WHERE" + 
					" id LIKE ? AND name LIKE ? AND address LIKE ? AND phone LIKE ?";
		return getForList(sql, cc.getId(), cc.getName(), cc.getAddress(), cc.getPhone());
	}

	@Override
	public long getCountWithId(String id) {
		String sql = "SELECT count(id) FROM customers WHERE id = ?";
		return getForValue(sql, id);
	}

	@Override
	public void update(Customer customer) {
		String sql = "UPDATE customers SET name=?, age=?, address=?, phone=?" +
					" WHERE id=?";
		update(sql, customer.getName(), customer.getAge(),
				customer.getAddress(), customer.getPhone(), customer.getId());
	}

}
