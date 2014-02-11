package me.xyzlast.bookstore.contexts;

/**
 * Created by ykyoon on 2/11/14.
 */
public class ConsolePrinter implements Printer {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
