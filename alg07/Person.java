

public class Person {
	String name, company, address, zipcode, phone, email;
	Person(String n, String c, String a, String z, String p, String e) {
		name = n.trim();
		company = c.trim();
		address = a.trim();
		zipcode = z.trim();
		phone = p.trim();
		email = e.trim();
	}

	public String toString() {
		String str = name + "\n";
		str += "\tCompany: " + company;
		str += "\n\tAddress: " + address;
		str += "\n\tZipcode: " + zipcode;
		str += "\n\tPhone: " + phone;
		str += "\n\tEmail: " + email;
		return str;
	}
}
