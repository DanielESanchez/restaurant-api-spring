package com.example.restaurantapi.model;

public abstract class Employee extends Person {
    private String job;
    private String  hireDate;
    private boolean isWorking;
    private float salary;
    public Employee(){
        super();
    }

    public abstract String getJob();

    public abstract String getHireDate();

    public abstract boolean getIsWorking();

    public abstract float getSalary();

    public abstract void setWorking(boolean working);
}