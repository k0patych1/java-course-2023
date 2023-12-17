package edu.hw10.task2;

public interface IFibCalculator {
    @Cache(persist = true)
    long fib(int number);
}
