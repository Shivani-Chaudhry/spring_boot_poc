package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.bean.BeanClass;

@Transactional
@Repository
public class MedicineDAO implements MedicineIntDAO {
	@PersistenceContext	
	private EntityManager entityManager;
	@SuppressWarnings("unchecked")
	@Override
	public List<BeanClass> getAllMedicine() {
		String hql = "FROM BeanClass as atcl ORDER BY atcl.id";
		return (List<BeanClass>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public BeanClass getMedicineById(int medicineId) {
		return entityManager.find(BeanClass.class, medicineId);
	}

	@Override
	public void addMedicine(BeanClass medicine) {
		entityManager.persist(medicine);

	}

	@Override
	public void updateMedicine(BeanClass medicine) {
		BeanClass artcl = getMedicineById(medicine.getId());
		artcl.setName(medicine.getName());
		artcl.setPrice(medicine.getPrice());
		artcl.setQuantity(medicine.getQuantity());
		entityManager.flush();

	}

	@Override
	public void deleteMedicine(int medicineId) {
		entityManager.remove(getMedicineById(medicineId));

	}

	@Override
	public boolean medicineExists(String name) {
		String hql = "FROM BeanClass WHERE name = '"+name+"'"; 
		return entityManager.createQuery(hql).getResultList().size() > 0 ? true : false;
	}

}
