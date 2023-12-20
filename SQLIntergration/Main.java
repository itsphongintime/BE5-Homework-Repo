package SQLIntergration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner keyboard = new Scanner(System.in);
        AccountServices AccountServices = new AccountServices();
        CourseServices CourseServices = new CourseServices();
        
        int loggedInAccountID = -1;
        
        showLoginAndRegisterMenuAndTheirFunctionalities(keyboard, AccountServices, CourseServices, loggedInAccountID);

        while (true) {
            CourseServices.showAllCourses();
            interactingWithCourseOptions (keyboard, AccountServices, CourseServices, loggedInAccountID);
        }
    }
    
    public static void showLoginAndRegisterMenuAndTheirFunctionalities (Scanner keyboard, AccountServices AccountServices, CourseServices CourseServices, int loggedInAccountID) throws SQLException {
    	final int SHOW_MENU_LOGIN = 1;
        final int SHOW_MENU_REGISTER = 2;
    	int input;
    	
    	do {
            System.out.println("----------------------");
            System.out.println("1. Login");
            System.out.println("2. Register");
            input = keyboard.nextInt();
            switch (input) {
                case SHOW_MENU_LOGIN:
                	loggedInAccountID = getLogInInfo(keyboard, AccountServices, loggedInAccountID);
                    break;

                case SHOW_MENU_REGISTER:
                	getRegisterInfo(keyboard, AccountServices);
                    break;

                default:
                    System.out.println("----------------------");
                    System.out.println("Invalid Input! Try Again!");
            }
        } while (loggedInAccountID <= 0);
    }
    
    public static int getLogInInfo (Scanner keyboard, AccountServices AccountServices, int loggedInAccountID) throws SQLException {
    	System.out.println("----------------------");
        System.out.print("Enter your Username: ");
        String inputID = keyboard.next();
        System.out.print("Enter your Password: ");
        String inputPassword = keyboard.next();
        loggedInAccountID = AccountServices.login(inputID, inputPassword);
        return loggedInAccountID;
    }
    
    public static void getRegisterInfo (Scanner keyboard, AccountServices AccountServices) throws SQLException {
    	System.out.println("----------------------");
        System.out.print("Enter your name: ");
        String inputName = keyboard.next();
        System.out.print("Enter your Username: ");
        String inputUsername = keyboard.next();
        System.out.print("Enter your Password: ");
        String inputPassword = keyboard.next();
        AccountServices.registerNewUser(inputName, inputUsername, inputPassword);
    }
    
    public static void interactingWithCourseOptions (Scanner keyboard, AccountServices AccountServices, CourseServices CourseServices, int loggedInAccountID) throws SQLException {
    	System.out.println("Choose a class to enroll into.");
        int courseChoice = keyboard.nextInt();
		
        if (courseChoice == 0) {
        	AccountServices.showAccountEnrolledCourses(loggedInAccountID);
        } else {
        	CourseServices.showCourseDetails(courseChoice);
            
        	int chosenOption = keyboard.nextInt();
        	
            final int SHOW_COURSE_MENTORS = 1;
            final int SHOW_ENROLL_RESULT = 2;
            
            switch (chosenOption) {
            case SHOW_COURSE_MENTORS:
                System.out.println("----------------------");
                CourseServices.getAllMentors();
                break;
    			
            case SHOW_ENROLL_RESULT:
            	AccountServices.enrolledInNewCourse(courseChoice, loggedInAccountID);
                System.out.println("----------------------");
                System.out.println("Class enrolled successfully!");
                break;
    			
            default:
    			
            }
        }
    }
}

