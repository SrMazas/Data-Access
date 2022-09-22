
package exercise;

import java.util.Scanner;

public class Application {
	public static void main(String[] args) {

		Business b = new Business("C:\\Users\\ID\\Downloads\\Objects.dat");

		Scanner sc = new Scanner(System.in);

		int menu;
		do {
			System.out.println("Choose an option: ");
			System.out.println();
			System.out.println("1. Add Person");
			System.out.println("2. Add Depart");
			System.out.println("3. See Person");
			System.out.println("4. See Depart");
			System.out.println("5. See Depart & Person");
			System.out.println("6. Delete Person");
			System.out.println("7. Delete Depart");
			System.out.println("8. Exit");
			System.out.print("--> ");
			menu = sc.nextInt();
			sc.nextLine();
			switch (menu) {
				case 1:
					b.addPerson();
					break;
				case 2:
					b.addDepart();
					break;
				case 3:
					b.showPerson();
					break;
				case 4:
					b.showDepart();
					break;
				case 5:
					b.showAll();
					break;
				case 6:
					break;
				case 7:
					break;
				case 8:
					break;
				default:
					System.out.println();
					System.out.println("+----------------+");
					System.out.println("| Invalid option |");
					System.out.println("+----------------+");
					System.out.println();
					break;
			}
		} while (menu != 8);

	}
}