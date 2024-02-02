package menu;

import models.*;
import service.*;
import utility.ApplicationContext;
import utility.Validation;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Menu {

    private final Scanner sc = new Scanner(System.in);

    UserService userService = ApplicationContext.getUserServiceImpel();
    AdminService adminService = ApplicationContext.getAdminServiceImpel();
    BranchService branchService = ApplicationContext.getBranchServiceImpel();
    SubBranchService subBranchService = ApplicationContext.getSubBranchServiceImpel();
    ProductService productService = ApplicationContext.getProductServiceImpel();

    public int getNumberFromUser() {
        int num = 0;
        try {
            num = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        } finally {
            sc.nextLine();
        }
        return num;
    }

    public float getFloatFromUser() {
        float num = 0;
        try {
            num = sc.nextFloat();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        } finally {
            sc.nextLine();
        }
        return num;
    }

    public String getStringFromUser() {
        String input = null;
        try {
            input = sc.nextLine();
        } catch (InputMismatchException e) {
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
            if (Validation.isValidPassword(password))
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
            if (Validation.isValidEmail(email))
                break;
            else
                System.out.println("plz enter a valid email");
        }
        return email;
    }

    public void startMenu() {
        System.out.println("************************** WELCOME TO MAKTAB ONLINE SHOP ***************************");
        System.out.println("press 1 for sign up");
        System.out.println("press 2 for log in");
        System.out.println("press 3 if you are admin");
        int startMenu = getNumberFromUser();
        if (startMenu > 3 || startMenu < 1) {
            System.out.println("plz enter valid number");
            startMenu();
        }
        switch (startMenu) {
            case 1 -> saveUser();
            case 2 -> logInUser();
            case 3 -> adminLogIn();
        }
    }

    public Users takeUserInfo() {
        System.out.println("plz enter your full name");
        String name = getStringFromUser();
        System.out.println("plz enter your username");
        String username = getStringFromUser();
        String password = getPasswordFromUser();
        String email = getEmailFromUser();
        return new Users(name, username, password, email);
    }

    public void saveUser() {
        Users user = takeUserInfo();
        int sve = 0;
        try {
            sve = userService.save(user);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (sve != 0) {
            System.out.println("welcome to the shop");
            System.out.println("now plz log in");
            logInUser();
        } else {
            System.out.println("something wrong plz try again");
            saveUser();
        }
    }

    public void logInUser() {
        System.out.println("plz enter your username");
        String username = getStringFromUser();
        System.out.println("plz enter your password");
        String password = getStringFromUser();
        Users user = null;
        try {
            user = userService.logIn(username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (user != null) {
            System.out.println("welcome dear " + user.getFullName());
            userBodyMenu(user);
        } else {
            System.out.println("wrong username or password");
            logInUser();
        }

    }

    public void adminLogIn() {
        System.out.println("plz enter your username");
        String username = getStringFromUser();
        System.out.println("plz enter your password");
        String password = getStringFromUser();

        Admins admin = null;
        try {
            admin = adminService.logIn(username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (admin != null) {
            System.out.println("welcome admin " + admin.getName());
            adminBodyMenu();
        } else {
            System.out.println("wrong username or password");
            adminLogIn();
        }
    }

    public void userBodyMenu(Users user) {
        System.out.println("by");
    }

    public void adminBodyMenu() {
        System.out.println("press 1 to asses branch menu");
        System.out.println("press 2 to asses sub branch menu");
        System.out.println("press 3 to asses products menu");
        System.out.println("press 4 to exit");
        int chooseAdmin = getNumberFromUser();
        if (chooseAdmin > 4 || chooseAdmin < 1) {
            System.out.println("plz enter valid number");
            adminBodyMenu();
        }
        switch (chooseAdmin) {
            case 1 -> branchMenu();
            case 2 -> subBranchMenu();
            case 3 -> productsMenu();
            case 4 -> System.out.println("good by");
        }
    }

    public void branchMenu() {
        System.out.println("press 1 to add one branch");
        System.out.println("press 2 to edit one branch");
        System.out.println("press 3 to delete one branch");
        System.out.println("press 4 to back");
        int chooseBranch = getNumberFromUser();
        if (chooseBranch > 4 || chooseBranch < 1) {
            System.out.println("plz enter valid number");
            branchMenu();
        }
        switch (chooseBranch) {
            case 1 -> saveOneBranch();
            case 2 -> editOneBranch();
            case 3 -> deleteBranchCheck();
            case 4 -> adminBodyMenu();
        }
    }

    public void subBranchMenu() {
        System.out.println("press 1 to add one sub branch");
        System.out.println("press 2 to edit one sub branch");
        System.out.println("press 3 to delete one sub branch");
        System.out.println("press 4 to back");
        int chooseSubBranch = getNumberFromUser();
        if (chooseSubBranch > 4 || chooseSubBranch < 1) {
            System.out.println("plz enter valid number");
            subBranchMenu();
        }
        switch (chooseSubBranch) {
            case 1 -> saveOneSubBranch();
            case 2 -> editSubBranch();
            case 3 -> deleteOneSubBranchFromInnerTable();
            case 4 -> adminBodyMenu();
        }
    }

    public void productsMenu() {
        System.out.println("press 1 to add products");
        System.out.println("press 2 to edit one product");
        System.out.println("press 3 to delete one product");
        System.out.println("press 4 to back");
        int chooseProduct = getNumberFromUser();
        if (chooseProduct > 4 || chooseProduct < 1) {
            System.out.println("plz enter valid number");
            productsMenu();
        }
        switch (chooseProduct) {
            case 1 -> saveOneProduct();
            case 2 -> editProducts();
            case 3 -> deleteOneProduct();
            case 4 -> adminBodyMenu();
        }
    }

    public void saveOneBranch() {
        System.out.println("plz enter the branch name");
        String name = getStringFromUser();
        Branch branch = new Branch(name);
        int saveBranch = 0;
        try {
            saveBranch = branchService.save(branch);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (saveBranch != 0) {
            System.out.println("branch saved");
            adminBodyMenu();
        } else {
            System.out.println("something wrong try again");
            branchMenu();
        }
    }

    public void editOneBranch() {

        showAllBranches();

        System.out.println("plz enter the name of branch you want to edit");
        String oldName = getStringFromUser();
        System.out.println("plz enter the branch new name");
        String newName = getStringFromUser();

        int editName = 0;
        try {
            editName = branchService.editName(oldName, newName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (editName != 0) {
            System.out.println("name changed");
            adminBodyMenu();
        } else {
            System.out.println("wrong name,plz try again");
            editOneBranch();
        }
    }

    public void showAllBranches() {
        Branch[] branches = null;

        try {
            branches = branchService.showAllBranches();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        assert branches != null;
        for (Branch branch : branches) {
            System.out.println(branch.toString());
        }
    }
    public void deleteBranchCheck() {
        try {
            System.out.println("plz check if there is any sub branch in product table first");
            System.out.println("is there any sub branch in product table?(y,n)");
            int check = deleteOneSubBranchFromInnerTableCheck();
            String checkInput = getStringFromUser();

            if (check != 0 && checkInput.equals("y")) {
                deleteOneBranchFromInnerTable();
            } else {
                deleteBranchCheck();
            }
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
            deleteBranchCheck();
        }
    }

    public void deleteOneBranchFromInnerTable() {

        showAllBranches();

        System.out.println("plz enter the name of branch you want to delete");
        String name = getStringFromUser();

        Branch branch = null;
        try {
            branch = branchService.findByName(name);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        int id = branch.getId();

        try {
            branchService.deleteFromInnerTable(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        deleteOneBranch(name);
    }

    public void deleteOneBranch(String name) {

        int deleteBranch = 0;
        try {
            deleteBranch = branchService.delete(name);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (deleteBranch != 0) {
            System.out.println("branch deleted");
            adminBodyMenu();
        } else {
            System.out.println("wrong name,plz try again");
            deleteOneBranchFromInnerTable();
        }
    }

    public Branch loudOneBranch() {

        showAllBranches();

        System.out.println("plz enter the branch name");
        String branchName = getStringFromUser();
        Branch branch = new Branch();
        try {
            branch = branchService.findByName(branchName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return branch;
    }

    public void saveOneSubBranch() {

        System.out.println("plz enter the sub branch name");
        String name = getStringFromUser();
        Branch branch = loudOneBranch();

        SubBranch subBranch = new SubBranch(name, branch);

        int saveSubBranch = 0;
        try {
            saveSubBranch = subBranchService.save(subBranch);
        } catch (SQLException ew) {
            System.out.println(ew.getMessage());
        }
        if (saveSubBranch != 0) {
            System.out.println("sub branch saved");
            adminBodyMenu();
        } else {
            System.out.println("something wrong try again");
            branchMenu();
        }
    }

    public void editSubBranch() {
        System.out.println("press 1 to edit name");
        System.out.println("press 2 to edit branch");
        System.out.println("press 3 to back");
        int chooseEditSubBranch = getNumberFromUser();
        if (chooseEditSubBranch > 2 || chooseEditSubBranch < 1) {
            System.out.println("plz enter valid number");
            editSubBranch();
        }
        switch (chooseEditSubBranch) {
            case 1 -> editOneSubBranchName();
            case 2 -> editSubBranchBranch();
            case 3 -> subBranchMenu();
        }
    }

    public void editOneSubBranchName() {

        showAllSubBranches();

        System.out.println("plz enter the name of sub branch you want to edit name");
        String name = getStringFromUser();
        System.out.println("plz enter the sub branch new name");
        String newName = getStringFromUser();

        int editSubBranchName = 0;
        try {
            editSubBranchName = subBranchService.editName(name, newName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (editSubBranchName != 0) {
            System.out.println("name changed");
            adminBodyMenu();
        } else {
            System.out.println("something wrong try again");
            editOneSubBranchName();
        }
    }

    public void editSubBranchBranch() {

        showAllSubBranches();

        try {
            System.out.println("plz enter the name of sub branch you want to edit branch");
            String name = getStringFromUser();

            System.out.println("all branches");
            showAllBranches();
            System.out.println("plz enter the sub branch new branch name");
            String newName = getStringFromUser();

            Branch branch = null;
            try {
                branch = branchService.findByName(newName);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            int newFk = branch.getId();
            int editSubBranchBranch = 0;
            try {
                editSubBranchBranch = subBranchService.editBranchFk(newFk, name);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            if (editSubBranchBranch != 0) {
                System.out.println("branch changed");
                adminBodyMenu();
            } else {
                System.out.println("something wrong try again");
                editSubBranchBranch();
            }
        } catch (NullPointerException e){
            System.out.println(e.getMessage());
            editSubBranchBranch();
        }
    }
    public void deleteOneSubBranchFromInnerTable() {

        showAllSubBranches();

        try {
            System.out.println("plz enter the name of sub branch you want to delete");
            String name = getStringFromUser();

            int id = 0;
            try {
                id = subBranchService.findByName(name).getId();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            try {
                subBranchService.deleteFromInnerTable(id);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            deleteOneSubBranch(name);
        } catch (NullPointerException e){
            System.out.println(e.getMessage());
            deleteOneSubBranchFromInnerTable();
        }
    }
    public int deleteOneSubBranchFromInnerTableCheck(){

        showAllSubBranches();

        System.out.println("plz enter the name of sub branch you want to delete");
        String name = getStringFromUser();

        int id = 0;
        try {
            id = subBranchService.findByName(name).getId();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        int check = 0;
        try {
         check = subBranchService.deleteFromInnerTable(id);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return check;
    }

    public void deleteOneSubBranch(String name) {



        int deleteOneSubBranch = 0;
        try {
            deleteOneSubBranch = subBranchService.delete(name);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (deleteOneSubBranch != 0) {
            System.out.println("branch deleted");
            adminBodyMenu();
        } else {
            System.out.println("something wrong try again");
            deleteOneSubBranchFromInnerTable();
        }
    }

    public void showAllSubBranches() {
        SubBranch[] subBranches = null;

        try {
            subBranches = subBranchService.showAllSubBranches();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            for (SubBranch subBranch : subBranches) {
                System.out.println(subBranch.toString());
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveOneProduct() {
        System.out.println("plz enter the product name");
        String productName = getStringFromUser();
        System.out.println("plz enter the price of the product");
        float price = getFloatFromUser();
        System.out.println("plz enter the number of the product");
        int number = getNumberFromUser();
        showAllSubBranches();
        System.out.println("plz enter the name of sub branch");
        String subBranchName = getStringFromUser();
        SubBranch subBranch = null;
        try {
            subBranch = subBranchService.findByName(subBranchName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Products product = new Products(productName, price, number, subBranch);

        int saveOneProduct = 0;
        try {
            saveOneProduct = productService.save(product);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (saveOneProduct != 0) {
            System.out.println("product saved");
            adminBodyMenu();
        } else {
            System.out.println("something wrong try again");
            saveOneProduct();
        }
    }

    public void editProducts() {
        System.out.println("press 1 to edit product name");
        System.out.println("press 2 to edit product price");
        System.out.println("press 3 to edit product number");
        System.out.println("press 4 to edit product sub branch");
        System.out.println("press 5 to back");
        int chooseEditProduct = getNumberFromUser();
        if (chooseEditProduct > 5 || chooseEditProduct < 1) {
            System.out.println("plz enter valid number");
            editProducts();
        }
            switch (chooseEditProduct) {
                case 1 -> editOneProductName();
                case 2 -> editProductPrice();
                case 3 -> editProductNumber();
                case 4 -> editProductSubBranch();
                case 5 -> productsMenu();
            }
        }

    public void showAllProducts() {
        Products[] products = null;

        try {
            products = productService.showAllProducts();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < Objects.requireNonNull(products).length; i++) {
            System.out.println(products[i].toString());
        }
    }

    public void editOneProductName() {

        showAllProducts();

        System.out.println("plz enter the name of product you want to edit name");
        String name = getStringFromUser();
        System.out.println("plz enter the product new name");
        String newName = getStringFromUser();

        int editProductName = 0;
        try {
            editProductName = productService.editName(name, newName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (editProductName != 0) {
            System.out.println("name changed");
            adminBodyMenu();
        } else {
            System.out.println("something wrong try again");
            editOneProductName();
        }
    }
    public void editProductPrice(){

        showAllProducts();

        System.out.println("plz enter the name of product you want to edit price");
        String name = getStringFromUser();
        System.out.println("plz enter the product new price");
        float newPrice = getFloatFromUser();

        int editProductPrice = 0;
        try {
            editProductPrice = productService.editProductPrice(name,newPrice);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        if (editProductPrice != 0) {
            System.out.println("price changed");
            adminBodyMenu();
        } else {
            System.out.println("something wrong try again");
            editProductPrice();
        }
    }
    public void editProductNumber(){

        showAllProducts();

        System.out.println("plz enter the name of product you want to edit number");
        String name = getStringFromUser();
        System.out.println("plz enter the product new number");
        int newNumber = getNumberFromUser();

        int editProductNumber = 0;
        try{
            editProductNumber = productService.editProductNumber(name,newNumber);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        if (editProductNumber != 0) {
            System.out.println("number changed");
            adminBodyMenu();
        } else {
            System.out.println("something wrong try again");
            editProductNumber();
        }
    }
    public void editProductSubBranch() {

        showAllProducts();

        try {
            System.out.println("plz enter the name of product you want to edit sub branch");
            String name = getStringFromUser();

            System.out.println("all sub branches");
            showAllSubBranches();
            System.out.println("plz enter the product new sub branch name");
            String newName = getStringFromUser();

            SubBranch subBranch = null;
            try {
                subBranch = subBranchService.findByName(newName);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            int newFk = subBranch.getId();
            int editProductSubBranch = 0;
            try {
                editProductSubBranch = productService.editProductSubBranch(newFk, name);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            if (editProductSubBranch != 0) {
                System.out.println("sub branch changed");
                adminBodyMenu();
            } else {
                System.out.println("something wrong try again");
                editProductSubBranch();
            }
        } catch (NullPointerException e){
            System.out.println(e.getMessage());
            editProductSubBranch();
        }
    }
    public void deleteOneProduct(){

        showAllProducts();

        System.out.println("plz enter the name of product you want to delete");
        String name = getStringFromUser();
        int deleteProduct = 0;
        try {
            deleteProduct = productService.delete(name);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        if (deleteProduct != 0) {
            System.out.println("product deleted");
            adminBodyMenu();
        } else {
            System.out.println("something wrong try again");
            deleteOneProduct();
        }
    }
}

