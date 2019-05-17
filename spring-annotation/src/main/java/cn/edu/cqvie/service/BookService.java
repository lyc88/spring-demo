package cn.edu.cqvie.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Service;

import cn.edu.cqvie.dao.BookDao;

@Service
public class BookService {

	// @Qualifier("bookDao3")
	// @Autowired(required = false)
//	@Resource(name = "bookDao2")
	// @Qualifier("bookDao")

	@Named("bookDao")
	@Inject()
	private BookDao bookDao;

	public void print() {
		System.out.println("bookDao == > " + bookDao);
	}
}
