package com.zj.mvcapp.Dao;

import java.util.List;

import com.zj.mvcapp.domain.Customer;

public interface CustomerDao {

	public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc);
	public List<Customer> getAll();
	public void save(Customer customer);
	public void update(Customer customer);
	public Customer get(String id);
	public void delete(String id);
	/**
	 * ���غ� name ��ȵļ�¼��
	 * @param name
	 * @return
	 */
	public long getCountWithId(String id);
	
}
