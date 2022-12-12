package ex1_textfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu extends MenuItem {
    private List<MenuItem> items = new ArrayList<>();
    public Menu(String name) {
        super(name);
    }

    @Override
    public void run() {

        while (true) {
            printMenuMessage();
            int choice = getUserChoice();
            if (choice == 0) {
                break;
            } else {
                items.get(choice - 1).run();
            }
        }
    }

    private void printMenuMessage() {
        // create menu message
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, items.get(i).name));
        }

        sb.append("0. Exit");

        System.out.println();
        printMenuName();
        System.out.println(sb.toString());
        System.out.println();
    }

    private void printMenuName() {
        StringBuilder menuName = new StringBuilder();
        for (int i = 0; i < 25 - name.length() / 2; i++) {
            menuName.append("=");
        }
        menuName.append(name);
        for (int i = 0; i < 25 - name.length() / 2; i++) {
            menuName.append("=");
        }

        System.out.println(menuName);
    }

    private int getUserChoice() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        int choice = 0;

        try {
            choice = Integer.parseInt(sc.nextLine());
            while (choice < 0 || choice > items.size()) {

                System.out.println("Invalid choice. Try again: ");
                choice = Integer.parseInt(sc.nextLine());
            }
        } catch (Exception e) {
            // NumberFormatException when user enters a character

        }

        return choice;
    }




    public void addItems(MenuItem item) {
        this.items.add(item);
    }



    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }


}
