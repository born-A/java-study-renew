package structure.decorator;

public class ParenthesesDecorator extends Decorator {

	public ParenthesesDecorator(Component component) {
		super(component);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String operation() {
		String text = component.operation();
		return "(" + text  + ")";
	}

}
