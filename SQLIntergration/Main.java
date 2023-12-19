package SQLIntergration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner keyboard = new Scanner(System.in);
        
        Database database = new Database();
        UserServices UserServices = new UserServices();
        CourseServices CourseServices = new CourseServices();
        
        showLoginAndRegisterMenuAndTheirFunctionalities(database, keyboard);

        while (true) {
            CourseServices.showAllCourses();
            interactingWithCourseOptions (keyboard, database, CourseServices);
        }
    }
    
    public static void showLoginAndRegisterMenuAndTheirFunctionalities (Database database, Scanner keyboard) {
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
                	UserServices.login(keyboard, database);
                    break;

                case SHOW_MENU_REGISTER:
                	UserServices.registerNewUser(keyboard, database);
                    break;

                default:
                    System.out.println("----------------------");
                    System.out.println("Invalid Input! Try Again!");
            }
        } while (database.getUser() == null);
    }
    
    public static void getLogInInfo (Scanner keyboard, Database database, AuthenticationService authService) {
    	System.out.println("----------------------");
        System.out.print("Enter your ID: ");
        String inputID = keyboard.next();
        System.out.print("Enter your Password: ");
        String inputPassword = keyboard.next();
        database.setUser(authService.logInAccount(inputID, inputPassword));
        if (database.getUser() == null) {
            System.out.println("----------------------");
            System.out.println("Wrong credentials! Try Again!");
        } else {
            if (database.getUser().getFailedAttempts() <= 3) {
                System.out.println("----------------------");
                System.out.println("Welcome " + database.getUser().getName());
            } else {
                System.out.println("----------------------");
                System.out.println("Your account is blocked!");
                database.setUser(null);
            }
        }
    }
    
    public static void getRegisterInfo (Scanner keyboard, Database database, AuthenticationService authService) {
    	System.out.println("----------------------");
        System.out.print("Enter your name: ");
        String inputName = keyboard.next();
        System.out.print("Enter your ID: ");
        String inputID = keyboard.next();
        System.out.print("Enter your Password: ");
        String inputPassword = keyboard.next();
        Account registeredAccount = new Account(inputID, inputPassword, inputName);
        authService.registerAccount(registeredAccount);
        System.out.println("----------------------");
        System.out.println("Account registered Successfully!");
    }
    
    public static void showUserEnrolledClasses (Scanner keyboard, Database database) {
    	int enrolledClassesIndex = 1;
    	if (database.getUser().enrolledCourses.equals(new ArrayList<Course>())) {
            System.out.println("----------------------");
            System.out.println("You have not enrolled in any courses!");
        } else {
            System.out.println("----------------------");
            System.out.println("You are currently enrolled in:");
            for (Course course : database.getUser().enrolledCourses) {
                System.out.print(enrolledClassesIndex + ". " + course.getName() + " - ");
                ArrayList<Mentor> courseMentors = course.getMentors();
                for (int j = 0; j < courseMentors.size(); j++) {
                    if (j == courseMentors.size() - 1) {
                        System.out.print(courseMentors.get(j).getName());
                    } else {
                        System.out.print(courseMentors.get(j).getName() + " - ");
                    }
                }
                System.out.println();
                enrolledClassesIndex++;
            }
        }
    }
    
    public static void interactingWithCourseOptions (Scanner keyboard, Database database, CourseServices CourseServices) {
    	System.out.println("Choose a class to enroll into.");
        String courseChoice = keyboard.next();
		
        if (courseChoice.equals("0")) {
        	showUserEnrolledClasses (keyboard, database);
        } else {
            int chosenClass = Integer.parseInt(courseChoice) - 1;
            if (chosenClass >= database.getCourses().size()) {
            	System.out.println("----------------------");
                System.out.println("Invalid Input! Try Again!");
                return;
            }
            CourseServices.chosenCourseDetails(database.getCourses().get(chosenClass));
            
            String chosenOption = keyboard.next();
            
            final String SHOW_COURSE_MENTORS = "1";
            final String SHOW_ENROLL_RESULT = "2";
            switch (chosenOption) {
            case SHOW_COURSE_MENTORS:
                System.out.println("----------------------");
                showMentorsOfChosenCourse(database.getCourses().get(chosenClass));
                break;
    			
            case SHOW_ENROLL_RESULT:
                database.getUser().enrolledCourses.add(database.getCourses().get(chosenClass));
                database.getCourses().get(chosenClass).setIsEnrolled(true);
                System.out.println("----------------------");
                System.out.println("Class enrolled successfully!");
                database.getCourses().remove(chosenClass);
                break;
    			
            default:
    			
            }
        }
    }
    
    public static void showMentorsOfChosenCourse(Course chosenCourse) {
        for (int i = 0; i < chosenCourse.getMentors().size(); i++) {
            System.out.println((i + 1) + ". " + chosenCourse.getMentors().get(i).getName());
        }
    }
}

