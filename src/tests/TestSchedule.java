package tests;

import schedule.Schedule;

public class TestSchedule {
    public static void main(String[] args) {
        Schedule schedule=new Schedule("src/tests/ressource/ex2_td.csv");
   //     Schedule schedule=new Schedule("src/tests/ressource/ex4_huge.csv");
        schedule.Observer();
    }
}
