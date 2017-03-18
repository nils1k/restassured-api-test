package com.vimond.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

/**
 * Created by kristian on 18/03/17.
 */
public class BookTest {

    @Test
    public void createBook() {
        Book myBook = new Book();

        myBook.setTitle("TEST123");
        myBook.setAuthor("Hr. Test");

        assertThat(myBook.getTitle(), is(equalTo("TEST123")));
        assertThat(myBook.getAuthor(), is(equalTo("Hr. Test")));
    }
}

