package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.bean.BeanClass;
import com.example.demo.service.ServiceInterface;
@Controller


public class ControllerClass {
	@Autowired
	private ServiceInterface medservice;



	@GetMapping("medicine/{id}")
	public ResponseEntity<BeanClass> getMedicineById(@PathVariable("id") Integer id) {
		BeanClass med =medservice.getMedicineById(id);
		return new ResponseEntity<BeanClass>(med, HttpStatus.OK);
	}

	@GetMapping("allmedicine")
	public ResponseEntity<List<BeanClass>> getAllMedicine(){
		List<BeanClass> l =medservice.getAllMedicine();
		return new ResponseEntity<List<BeanClass>>(l, HttpStatus.OK);

	}

	@PostMapping("add")
	public ResponseEntity<String> addMedicine(@RequestBody BeanClass medicine, UriComponentsBuilder builder) {
		boolean flag = medservice.addMedicine(medicine);
		if (flag == false) {
			return new ResponseEntity<String>("Medicine already exists",HttpStatus.CONFLICT);
		}

		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@PutMapping("update")
	public ResponseEntity<BeanClass> updateMedicine(@RequestBody BeanClass medicine) {
		medservice.updateMedicine(medicine);
		return new ResponseEntity<BeanClass>(medicine, HttpStatus.OK);
	}
	@DeleteMapping("medicine/{id}")
	public ResponseEntity<Void> deleteMedicine(@PathVariable("id") Integer id) {
		medservice.deleteMedicine(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
}  


