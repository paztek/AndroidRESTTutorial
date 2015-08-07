package com.paztek.resttutorial.content.model;

/**
 * @author matthieu
 */
public class Category {

    private static final String TAG = Category.class.getSimpleName();

    protected String name;

    public Category() {
        this(null);
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
