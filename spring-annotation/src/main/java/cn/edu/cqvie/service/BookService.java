package cn.edu.cqvie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.cqvie.dao.BookDao;

@Service
public class BookService {
	
	@Autowired
	private BookDao bookDao;
	
	public void print() {
		System.out.println("bookDao == > " + bookDao.toString());
	}
}
