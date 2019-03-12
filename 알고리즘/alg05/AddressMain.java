package alg05;

import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class AddressMain {
	Vector<People> Addressbook;

	public static void main(String[] args) {
		new AddressMain();
	}

	AddressMain() {
		readData();
		Scanner kb = new Scanner(System.in);
		while (true) {
			System.out.print("정렬기준을 선택해주세요 1.이름  2.회사  3.주소  4.우편번호 5.전화번호 6.이메일주소\n1~6:");
			switch (kb.nextInt()) {
			case 1:
				sortByName();
				print();
				break;
			case 2:
				sortByCompany();
				print();
				break;
			case 3:
				sortByAddress();
				print();
				break;
			case 4:
				sortByZipcode();
				print();
				break;
			case 5:
				sortByPhone();
				print();
				break;
			case 6:
				sortByEmail();
				print();
				break;
			default:
				kb.close();
				return;
			}
		}
	}

	private void print() {
		for (People people : Addressbook)
			System.out.println(people);
	}

	private void sortByEmail() {
		Addressbook.sort(new Comparator<People>() {
			@Override
			public int compare(People o1, People o2) {
				return o1.email.compareTo(o2.email);
			}
		});
	}

	private void sortByPhone() {
		Addressbook.sort(new Comparator<People>() {
			@Override
			public int compare(People o1, People o2) {
				return o1.phone.compareTo(o2.phone);
			}
		});
	}

	private void sortByZipcode() {
		Addressbook.sort(new Comparator<People>() {
			@Override
			public int compare(People o1, People o2) {
				return o1.zipcode.compareTo(o2.zipcode);
			}
		});
	}

	private void sortByAddress() {
		Addressbook.sort(new Comparator<People>() {
			@Override
			public int compare(People o1, People o2) {
				return o1.address.compareTo(o2.address);
			}
		});
	}

	private void sortByCompany() {
		Addressbook.sort(new Comparator<People>() {
			@Override
			public int compare(People o1, People o2) {
				return o1.company.compareTo(o2.company);
			}
		});
	}

	private void sortByName() {
		Addressbook.sort(new Comparator<People>() {
			@Override
			public int compare(People o1, People o2) {
				return o1.name.compareTo(o2.name);
			}
		});
	}

	private void readData() {
		Scanner read = new Scanner(AddressMain.class.getResourceAsStream("address.txt"));
		Addressbook = new Vector<People>();
		while (read.hasNext()) {
			StringTokenizer token = new StringTokenizer(read.nextLine(), "|");
			Addressbook.add(new People(token.nextToken(), token.nextToken(), token.nextToken(), token.nextToken(),
					token.nextToken(), token.nextToken()));
		}
		read.close();
	}
}