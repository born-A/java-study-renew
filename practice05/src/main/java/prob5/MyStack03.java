package prob5;

public class MyStack03<T> {
    private T[] buffer;
    private int top;
    
    @SuppressWarnings("unchecked")
    public MyStack03(Class<?> klass, int capacity) {
    	top = -1;
    	buffer = (T[])new Object[capacity];
    	// buffer = (T[])Array.newInstance(klass, capacity);
    }
}
