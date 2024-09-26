package com.myproject.library.service.impl;

import com.myproject.library.repository.BookRepository;
import com.myproject.library.service.IBookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookService implements IBookService {
    BookRepository bookRepository;




}
