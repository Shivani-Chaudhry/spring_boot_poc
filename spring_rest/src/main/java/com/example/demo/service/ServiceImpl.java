package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.BeanClass;
import com.example.demo.dao.MedicineIntDAO;

@Service
public class ServiceImpl implements ServiceInterface
{
	@Autowired
	private MedicineIntDAO medicineDAO;

	@Override
	public List<BeanClass> getAllMedicine() {
		return medicineDAO.getAllMedicine();
	}

	@Override
	public BeanClass getMedicineById(int medicineId) {
		BeanClass obj=medicineDAO.getMedicineById(medicineId);
		return obj;
	}

	@Override
	public boolean addMedicine(BeanClass medicine) {
		if (medicineDAO.medicineExists(medicine.getName())) {
            return false;
        } else {
		medicineDAO.addMedicine(medicine);
		return true;
	}
	}
	



	@Override
	public void updateMedicine(BeanClass medicine) {
		medicineDAO.updateMedicine(medicine);

	}

	@Override
	public void deleteMedicine(int medicineId) {
		medicineDAO.deleteMedicine(medicineId);

	}


}
