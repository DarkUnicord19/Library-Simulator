/******************************************************************************

Certainly! Here's another simple project idea you can work on using Java:

Project Idea: Library Management System

Description: Create a console-based application for managing a library's inventory. 
Users should be able to add books, search for books, borrow and return books, 
and view the available books.

Start by designing the Book class with its attributes and methods. 
Then, create the necessary classes to manage the library's inventory 
and perform operations such as adding, searching, borrowing, and returning books.

Implement the user input handling and error handling to 
ensure a smooth user experience. Then, incorporate data persistence to 
save and load the book inventory from a file.

Throughout the project, focus on writing clean, modular code and 
following object-oriented programming principles. Break down the functionalities 
into separate methods and classes to improve code reusability and maintainability.

By completing this project, you'll gain experience with Java fundamentals, 
data structures, file I/O operations, and basic user interface design. 
It's also a practical project to showcase your programming skills.

Feel free to customize the project and add additional features based on 
your preferences and interests. Good luck with your library management system project!

*******************************************************************************/
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

class Book{
    String title, author, isbn;
    boolean availabilityStatus;
    
    public Book(String t, String a, String i){
        title = t;
        author = a;
        isbn = i;
        //all newly added books are readily available in the library until further notice
        availabilityStatus = true;  
    }
    
    public void displayBook(){
        //display book information in following format:
        //title by author - isbn - available | unavailable
        System.out.print(title+" by "+author+" - "+isbn+" - ");
        if(availabilityStatus)
        System.out.print("available\n");
        else
        System.out.print("unavailable\n");
    }
    public void checkoutBook(){
        //allows the user to checkout a Book
        //sets the availability status to false
        availabilityStatus = false;
    }
    public void returnBook(){
        //allows the user to return a Book
        //sets the availability status to true
        availabilityStatus = true;
    }
}

public class Main //library
{
	//	Key Features:
    /*1. Book Class: Create a Book class with attributes such as title, 
    author, ISBN, and availability status.*/
    
    /*2. Book Inventory: Implement a data structure, such as an ArrayList or HashMap, 
    to store and manage the library's collection of books.*/
    ArrayList <Book> LibraryInventory = new ArrayList <Book>();
    Scanner kb = new Scanner(System.in);
    
    /*3. Book Management: Enable users to add new books to the inventory, 
    remove books, and update the availability status of books.*/
    public void addBook(){
        //prompt the user to enter book information
        System.out.println("You have chosen to add a new book to the library.");
        System.out.println("Please type in the Title of the book: ");
        String t = kb.nextLine();
        System.out.println("Please type in the Author of the book: ");
        String a = kb.nextLine();
        System.out.println("Please type in the ISBN of the book: ");
        String i = kb.nextLine();
        //create a new Book Object from a title, author, and ISBN
        Book b = new Book(t, a, i);
        //add the new book to the ArrayList LibraryInventory
        LibraryInventory.add(b);
    }
    public void removeBook(){
        //removes a book from the Library catalogue permanently (different from checking out a book)
        System.out.println("You have decided to remove a book from the library's inventory.");
        //search for a Book
        System.out.println("Before we can get started you must search for a book.");
        Book unWantedBook = searchBook();
        //remove the selected book
        System.out.print("You have chosen to remove ");
        unWantedBook.displayBook();
        System.out.println("It will now be removed.");
        LibraryInventory.remove(unWantedBook);
    }
    
    
    /*4. Book Search: Implement a search functionality that allows users 
    to search for books by title, author, or ISBN. Display the search results to the user.*/
    public Book searchBook(){
        //prompts user to enter a title, author, or ISBN
        System.out.println("You have decided to search for a book.");
        System.out.println("Please enter a title, author, or ISBN: ");
        String input = kb.nextLine();
        
        ArrayList <Book> MatchingTitle = new ArrayList <Book>();
        ArrayList <Book> MatchingAuthor = new ArrayList <Book>();
        ArrayList <Book> MatchingISBN = new ArrayList <Book>();
        
        for(Book b : LibraryInventory){
            if(b.title.equals(input))
            MatchingTitle.add(b);
            if(b.author.equals(input))
            MatchingAuthor.add(b);
            if(b.isbn.equals(input))
            MatchingISBN.add(b);
        }
        
        if(MatchingTitle.size() == 0 && MatchingAuthor.size() == 0 && MatchingISBN.size() == 0){
            System.out.println("\nThere are no books that could be found.");
            return null;
        }
        
        //displays a list of books with matching title
        System.out.println("[1] Here is the list of books with a title that matches "+input);
        for(Book b : MatchingTitle){
            //System.out.print("["+MatchingTitle.indexOf(b)+"] ");
            b.displayBook();
            if(MatchingTitle.size() == 0)
            System.out.println("There are no books with a matching title.");
        }
        System.out.println();
        //displays a list of books with matching author
        System.out.println("[2] Here is the list of books with an author that matches "+input);
        for(Book b : MatchingAuthor){
            //System.out.print("["+MatchingAuthor.indexOf(b)+"] ");
            b.displayBook();
            if(MatchingAuthor.size() == 0)
            System.out.println("There are no books with a matching author name.");
        }
        System.out.println();
        //displays a list of books with matching ISBN
        System.out.println("[3] Here is the list of books with an ISBN that matches "+input);
        for(Book b : MatchingISBN){
            //System.out.print("["+MatchingISBN.indexOf(b)+"] ");
            b.displayBook();
            if(MatchingISBN.size() == 0)
            System.out.println("There are no books with a matchint ISBN.");
        }
        System.out.println();
        //allows user to select desired book
        System.out.println("Please select the number of the list your book is in");
        System.out.println("i.e. if it is in the matching title list please enter 1: ");
        
        int listSelection = kb.nextInt();
        switch(listSelection){
            case 1:
                System.out.println();
                System.out.println("Here is the list of books with a title that matches "+input);
                for(Book b : MatchingTitle){
                    System.out.print("   ["+MatchingTitle.indexOf(b)+"] ");
                    b.displayBook();
                }
                System.out.println();
                System.out.println("Please select the number next to your selected book: ");
                int bookSelection1 = kb.nextInt();
                kb.nextLine();
                MatchingTitle.get(bookSelection1).displayBook();
                return MatchingTitle.get(bookSelection1);
            case 2:
                System.out.println();
                System.out.println("Here is the list of books with an author that matches "+input);
                for(Book b : MatchingAuthor){
                    System.out.print("   ["+MatchingAuthor.indexOf(b)+"] ");
                    b.displayBook();
                }
                System.out.println();
                System.out.println("Please select the number next to your selected book: ");
                int bookSelection2 = kb.nextInt();
                kb.nextLine();
                MatchingAuthor.get(bookSelection2).displayBook();
                return MatchingAuthor.get(bookSelection2);
            case 3:
                System.out.println();
                System.out.println("Here is the list of books with an ISBN that matches "+input);
                for(Book b : MatchingISBN){
                    System.out.print("   ["+MatchingISBN.indexOf(b)+"] ");
                    b.displayBook();
                }
                System.out.println();
                System.out.println("Please select the number next to your selected book: ");
                int bookSelection3 = kb.nextInt();
                kb.nextLine();
                MatchingISBN.get(bookSelection3).displayBook();
                return MatchingISBN.get(bookSelection3);
            default:
                //display an error message
                kb.nextLine();
                return null;
        }
    }
    
    /*5. Borrowing and Returning: Allow users to borrow books by marking them
    as unavailable and associating the borrower's information. Implement a feature to 
    return borrowed books and update their availability status.*/
    public void checkoutBook(Book b){
        b.checkoutBook();
    }
    public void returnBook(Book b){
        b.returnBook();
    }
    
    /*6. Book Display: Provide an option to display all the books in the library's inventory. 
    Display the book details in a formatted manner for easy readability.*/
    public void displayInventory(){
        //display the entire inventory of the library
        System.out.println("You have decided to view the entire inventory.");
        System.out.println("Please Stand By.");
        System.out.println();
        for(Book b : LibraryInventory){
            b.displayBook();
            System.out.println();
        }
        
    }
    
    /*7. User Input Handling: Prompt the user for input, validate it, 
    and handle any potential errors gracefully. Provide clear instructions and 
    appropriate feedback to guide the user through different operations.*/
    
    /*8. Data Persistence: Implement a mechanism to store the library's inventory data, 
    so it persists even after the application is closed. You can use file I/O to save and 
    load the book data from a text file.*/
    public void saveInventoryAsTextFile(){
        //saves inventory as a txt file
    }
    
    public void loadTextFileToInventory(){
        //loads the data from the txt file into the Inventory
    }
    
    /*9. Error Handling: Implement error handling to handle cases such as invalid inputs, 
    book not found, or trying to borrow a book that is already unavailable. 
    Display informative error messages to the user.*/
        //public void invalidInput(){ }
        //public void bookNotFound(){ }
        //public void bookUnavailable(){ }
        
    /*10. User-Friendly Interface: Design a user-friendly console interface with clear menus 
    and prompts to guide users through different operations.*/
    public static void main(String[] args) {
        Main library = new Main();
        Scanner kb2 = new Scanner(System.in);
	    //Welcome menu
	    System.out.println("Welcome to the Simulated Library's Online System.");
	    System.out.println();
	    
	    //Ask user if they are a patron or a librarian
	    System.out.println("Select which type of user you are: ");
	    System.out.println("[0] Exit");
	    System.out.println("[1] Patron");
	    System.out.println("[2] Librarian");
	    
	    int userStatus = kb2.nextInt();
	    if(userStatus==0)
	        System.exit(0);
	    //If they are a patron, they can search for, checkout, or return books
	    //If they are a librarian, they can search for, checkout, or return books.
	    //Librarians can also manipulate the inventory by adding or removing books.
	    
	    boolean continueBrowsing = true;
	    do{
	        System.out.println();
	        System.out.println("Please select what action you would like to do: ");
    	    System.out.println("[0] Exit");
    	    System.out.println("[1] Search for a book");
    	    System.out.println("[2] Checkout a book");
    	    System.out.println("[3] Return a book");
    	    if(userStatus == 2){
    	        System.out.println("[4] Add a book to the inventory");
    	        System.out.println("[5] Remove a book from the inventory");
    	    }
    	    
    	    int choice = kb2.nextInt();
    	    
    	    System.out.println();
    	    
    	    switch(choice){
    	        case 0:
    	            continueBrowsing = false;
    	            break;
    	        case 1:
    	            library.searchBook();
    	            break;
    	        case 2:
    	            System.out.println("Find the book before checking out. ");
    	            library.checkoutBook(library.searchBook());
    	            break;
    	        case 3:
    	            System.out.println("Find the book before returning it. ");
    	            library.returnBook(library.searchBook());
    	            break;
    	        case 4:
    	            if(userStatus == 2){
    	                library.addBook();
    	                break;
    	            }
    	        case 5:
    	            if(userStatus == 2){
    	                library.removeBook();
    	                break;
    	            }
    	        default:
    	            System.out.println();
    	            System.out.print("You have pressed an invalid key.");
    	            
    	        
    	    }
    	 }while(continueBrowsing);
	    
	}
}
