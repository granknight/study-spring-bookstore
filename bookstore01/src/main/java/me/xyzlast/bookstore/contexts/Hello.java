package me.xyzlast.bookstore.contexts;

/**
 * Created by ykyoon on 2/11/14.
 */
public class Hello {
    private String name;
    private Printer printer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Printer getPrinter() {
        return printer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public String sayHello() {
        return "Hello " + name;
    }

    public void print() {
        printer.print(sayHello());
    }
}
