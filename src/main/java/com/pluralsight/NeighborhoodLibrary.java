package com.pluralsight;

import java.util.Scanner;

public class NeighborhoodLibrary {


    private static Book[] books = new Book[20]; 

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        books[0] = new Book(100001, "0-0-0-1", "Blood Meridian by Cormac McCarthy", "", false); // Populate the library with 5 books
        books[1] = new Book(100002, "0-0-0-2", "Necronomicon by Unknown", "", false);
        books[2] = new Book(100003, "0-0-0-3", "Great Gatsby by F. Scott Fitzgerald", "", false);
        books[3] = new Book(100004, "0-0-0-4", "Fear and Loathing by Hunter Thompson", "", false);
        books[4] = new Book(100005, "0-0-0-5", "Diary of a Wimpy Kid by Jeff Kinney", "", false);

        while (true) {
            // Displays options then prompts user to input a selection
            System.out.println("=".repeat(30)+"\nWelcome to Adam's library!\n"+
                    "=".repeat(30)+"\nPlease input a number (1-3)\n"+"=".repeat(30)+
                    "\n1.Show Available Books\n2. Show Checked Out Books\n3. Exit\n"+
                    "-".repeat(30));
            int command = userInput.nextInt();
            userInput.nextLine();
            switch (command) {
                case 1: // List books, then prompt the user to select a book or return to menu selection
                    if (hasBooks(false))
                        displayBooks(false);
                    else break;

                    System.out.println("Check out a book - (C)\n" + "Go back to the home screen - (X)");
                    if (userInput.next().equalsIgnoreCase("c") && hasBooks(false))
                        selectBook(userInput, false);
                    break;
                case 2:
                    if (hasBooks(true))
                        displayBooks(true);
                    else break;

                    System.out.println("Check In a Book - (C) \n" + "Go back to the home screen - (X) ");
                    if (userInput.next().equalsIgnoreCase("c") && hasBooks(true)) {
                        selectBook(userInput, true);
                    }
                    break;
                default:
                    System.out.println("Thanks for visiting!");
                    return;
            }
        }
    }
    // Displays books in formatted message, displaying current holder only if the book is checked out (via toString).
    public static void displayBooks(boolean checkedOut) {
        System.out.println("-".repeat(75));
        for (Book book : books) {
            if (book != null && book.isCheckedOut() == checkedOut) {
                System.out.println(book);
            }
        }System.out.println("-".repeat(75));

    }
    // Checks whether there are books to display and if those books are checked out, if there are no books - return to main menu.
    public static boolean hasBooks(boolean checkedOut) {
        for (Book book : books)
            if (book != null && book.isCheckedOut() == checkedOut) return true;
        System.out.println("-".repeat(30)+"\nNo books to display!");
        return false;
    }

    // Searches through an Array of books, if userInput matches an ID, perform checkIn/checkOut depending on given parameter.
    public static void selectBook (Scanner userInput, boolean checkIn) {
        System.out.print("Please select a book by entering the ID: ");
        int inputID = userInput.nextInt();
        userInput.nextLine();

        for (Book book : books) {
            if (book.getId() == inputID && book.isCheckedOut() == checkIn) {
                System.out.println("You have selected: " + book.getTitle());
                if (!checkIn){
                    System.out.print("Please enter your name: ");
                    book.checkOut(userInput.nextLine());
                }
                else book.checkIn();
                System.out.println("-".repeat(40));
                System.out.println("Success! Thank you for your patronage!");
                break;
            }
        }
    }
}