import java.util.*;

public class BankAccountTester {
	public static void main(String[] args) {
		BankAccount a = new BankAccount("A", 5);
		BankAccount b = new BankAccount("B", 4);
		BankAccount c = new BankAccount("C", 3);
		BankAccount d = new BankAccount("D", 2);
		BankAccount e = new BankAccount("E", 1);
		BankAccount f = new BankAccount("F", 2);
		BankAccount g = new BankAccount("G", 3);
		BankAccount h = new BankAccount("H", 4);
		BankAccount i = new BankAccount("I", 5);
		BankAccount j = new BankAccount("J", 6);
		BankAccount k = new BankAccount("K", 7);
		BankAccount l = new BankAccount("L", 8);
		BankAccount m = new BankAccount("M", 9);
		BankAccount n = new BankAccount("N", 8);
		BankAccount o = new BankAccount("O", 7);
		
		ArrayList<BankAccount> list = new ArrayList<>();
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);
		list.add(e);
		list.add(f);
		list.add(g);
		list.add(h);
		list.add(i);
		list.add(j);
		list.add(k);
		list.add(l);
		list.add(m);
		list.add(n);
		list.add(o);
		
		System.out.println("Pre-Sorted");
		for (BankAccount temp : list) {
			System.out.println(temp.getInfo());
		}
		
		Collections.sort(list);
		System.out.println("Post-Sorted");
		for (BankAccount temp : list) {
			System.out.println(temp.getInfo());
		}
	}
}
