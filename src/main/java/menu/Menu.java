package menu;

import models.Users;
import service.UserService;
import utility.ApplicationContext;
import utility.Validation;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private final Scanner sc = new Scanner(System.in);

    UserService userService = ApplicationContext.getUserServiceImpel();

    public int getNumberFromUser(){
        int num = 0;
        try {
         num =  sc.nextInt();
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }
        finally {
            sc.nextLine();
        }
        return num;
    }
    public String getStringFromUser(){
       String input = null;
        try {
         input =  sc.nextLine();
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }
        return input;
    }
    private String getPasswordFromUser() {
        String password;
        while (true) {
            System.out.println("Please enter your password:");
            System.out.println("Hint: it has to be between 8 to 10 and must contain at least 1 lower and upper case and 1 digit and 1 char ");
            password = getStringFromUser();
            if(Validation.isValidPassword(password))
                break;
            else
                System.out.println("plz enter a valid password");
        }
        return password;
    }

    private String getEmailFromUser() {
        String email;
        while (true) {
            System.out.println("Please enter your email:");
            email = getStringFromUser();
            if(Validation.isValidEmail(email))
                break;
            else
                System.out.println("plz enter a valid email");
        }
        return email;
    }
    public void startMenu(){
        System.out.println("************************** WELCOME TO MAKTAB ONLINE SHOP ***************************");
        System.out.println("press 1 for sign up");
        System.out.println("press 2 for log in");
        System.out.println("press 3 if you are admin");
        int startMenu = getNumberFromUser();
        if (startMenu > 3 || startMenu < 1){
            System.out.println("plz enter valid number");
            startMenu();
        }
        switch (startMenu){
            case 1 -> saveUser();
            case 2 -> logIn();
            case 3 -> adminLogIn();
        }
    }
    public Users takeUserInfo(){
        System.out.println("plz enter your full name");
        String name = getStringFromUser();
        System.out.println("plz enter your username");
        String username = getStringFromUser();
        String password = getPasswordFromUser();
        String email = getEmailFromUser();
        Users user = new Users(name,username,password,email);
        return user;
    }
    public void saveUser(){
        Users user = takeUserInfo();
        int sve = 0;
        try {
          sve  = userService.save(user);
        }catch (SQLException e)  {
            System.out.println(e.getMessage());
        }
        if (sve != 0) {
            System.out.println("welcome to the shop");
            System.out.println("now plz log in");
            logIn();
        }
        else {
            System.out.println("something wrong plz try again");
            saveUser();
        }
    }
    public void logIn(){
        System.out.println("plz enter your username");
        String username = getStringFromUser();
        System.out.println("plz enter your password");
        String password = getStringFromUser();
        Users user = null;
        try {
          user  = userService.logIn(username,password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        if (user != null){
            System.out.println("welcome dear " + user.getFullName());
            userBodyMenu();
        }
        else {
            System.out.println("wrong username or password");
            logIn();
        }

    }
    public void adminLogIn(){
        System.out.println("hi admin");
    }
    public void userBodyMenu(){
        System.out.println("by");
    }

}
