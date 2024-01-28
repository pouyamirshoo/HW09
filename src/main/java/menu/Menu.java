package menu;

import models.Admins;
import models.Branch;
import models.SubBranch;
import models.Users;
import service.AdminService;
import service.BranchService;
import service.SubBranchService;
import service.UserService;
import utility.ApplicationContext;
import utility.Validation;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private final Scanner sc = new Scanner(System.in);

    UserService userService = ApplicationContext.getUserServiceImpel();
    AdminService adminService = ApplicationContext.getAdminServiceImpel();
    BranchService branchService = ApplicationContext.getBranchServiceImpel();
    SubBranchService subBranchService = ApplicationContext.getSubBranchServiceImpel();

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
            case 2 -> logInUser();
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
        return new Users(name,username,password,email);
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
            logInUser();
        }
        else {
            System.out.println("something wrong plz try again");
            saveUser();
        }
    }
    public void logInUser(){
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
            logInUser();
        }

    }
    public void adminLogIn(){
        System.out.println("plz enter your username");
        String username = getStringFromUser();
        System.out.println("plz enter your password");
        String password = getStringFromUser();

        Admins admin = null;
        try {
         admin = adminService.logIn(username,password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        if(admin != null){
            System.out.println("welcome admin " + admin.getName());
            adminBodyMenu();
        }
        else {
            System.out.println("wrong username or password");
            adminLogIn();
        }
    }
    public void userBodyMenu(){
        System.out.println("by");
    }
    public void adminBodyMenu(){
        System.out.println("press 1 to asses branch menu");
        System.out.println("press 2 to asses sub branch menu");
        int chooseAdmin = getNumberFromUser();
        if (chooseAdmin > 3 || chooseAdmin < 1){
            System.out.println("plz enter valid number");
            adminBodyMenu();
        }
        switch (chooseAdmin){
            case 1 -> branchMenu();
            case 2 -> subBranchMenu();
        }
    }
    public void branchMenu(){
        System.out.println("press 1 to add one branch");
        System.out.println("press 2 to edit one branch");
        System.out.println("press 3 to delete one branch");
        System.out.println("press 4 to back");
        int chooseBranch = getNumberFromUser();
        if (chooseBranch > 4 || chooseBranch < 1){
            System.out.println("plz enter valid number");
            branchMenu();
        }
        switch (chooseBranch){
            case 1 -> saveOneBranch();
            case 2 -> editOneBranch();
            case 3 -> deleteOneBranch();
            case 4 -> adminBodyMenu();
        }
    }
    public void subBranchMenu(){
        System.out.println("press 1 to add one sub branch");
        System.out.println("press 2 to edit one sub branch");
        System.out.println("press 3 to delete one sub branch");
        System.out.println("press 4 to back");
        int chooseSubBranch = getNumberFromUser();
        if (chooseSubBranch > 4 || chooseSubBranch < 1){
            System.out.println("plz enter valid number");
            subBranchMenu();
        }
        switch (chooseSubBranch){
            case 1 -> saveOneSubBranch();
        }
    }
    public void saveOneBranch(){
        System.out.println("plz enter the branch name");
        String name = getStringFromUser();
        Branch branch = new Branch(name);
        int saveBranch = 0;
        try {
            saveBranch = branchService.save(branch);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        if(saveBranch != 0){
            System.out.println("branch saved");
            adminBodyMenu();
        }
        else {
            System.out.println("something wrong try again");
            branchMenu();
        }
    }
    public void editOneBranch(){

        showAllBranches();

        System.out.println("plz enter the name of branch you want to edit");
        String oldName = getStringFromUser();
        System.out.println("plz enter the branch new name");
        String newName = getStringFromUser();

        int editName = 0;
        try {
           editName = branchService.editName(oldName,newName);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        if(editName!=0){
            System.out.println("name changed");
            adminBodyMenu();
        }
        else {
            System.out.println("wrong name,plz try again");
            editOneBranch();
        }
    }
    public void showAllBranches(){
        Branch [] branches = null;

        try {
          branches =  branchService.showAllBranches();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        for (Branch branch : branches) {
            System.out.println(branch.toString());
        }
    }
    public void deleteOneBranch(){

        showAllBranches();

        System.out.println("plz enter the name of branch you want to delete");
        String name = getStringFromUser();

        int deleteBranch = 0;
        try {
            deleteBranch = branchService.delete(name);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        if(deleteBranch!=0){
            System.out.println("branch deleted");
            adminBodyMenu();
        }
        else {
            System.out.println("wrong name,plz try again");
            deleteOneBranch();
        }
    }
    public Branch loudOneBranch(){

        showAllBranches();

        System.out.println("plz enter the branch name");
        String branchName = getStringFromUser();
        Branch branch = new Branch();
        try {
            branch = branchService.findByName(branchName);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return branch;
    }
    public void saveOneSubBranch(){

        System.out.println("plz enter the sub branch name");
        String name = getStringFromUser();
        Branch branch = loudOneBranch();

        SubBranch subBranch = new SubBranch(name,branch);

        int saveSubBranch = 0;
        try {
            saveSubBranch = subBranchService.save(subBranch);
        }catch (SQLException ew){
            System.out.println(ew.getMessage());
        }
        if(saveSubBranch != 0){
            System.out.println("sub branch saved");
            adminBodyMenu();
        }
        else {
            System.out.println("something wrong try again");
            branchMenu();
        }
    }
}
