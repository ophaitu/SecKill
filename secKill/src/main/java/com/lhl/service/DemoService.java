package com.lhl.service;

import com.lhl.dao.DemoDao;
import com.lhl.domain.Demo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class DemoService {
	
	@Resource
	DemoDao demoDao;
	
	public Demo getByName(String name) {
		 return demoDao.getByName(name);
	}

	@Transactional
	public boolean tx() {
		Demo u1= new Demo();
		u1.setName("qs");
		u1.setContent("1");
		demoDao.insert(u1);

		Demo u2= new Demo();
		u2.setName("qs");
		u2.setContent("啊");
		demoDao.insert(u2);
		
		return true;
	}
	
}
