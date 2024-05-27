package behavioral.observer;

public class Client {

	public static void main(String[] args) {
		Subject<Integer> intSubject = new Subject<>();
		
		intSubject.registerObserver(new Observer<Integer>() {

			@Override
			public void update(Integer val) {
				System.out.println();
				
			}
			
		});

	}

}
