package com.atguigu.service;


import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atguigu.dao.BookDao;


@Service
public class BookService {




//	@Inject
//	@Qualifier("bookDao")
//    @Autowired(required = false)
    @Resource(name="bookDao")
	private BookDao bookDao2;

	@Override
	public String toString() {
		return "BookService [bookDao=" + bookDao2 + "]";
	}
}
