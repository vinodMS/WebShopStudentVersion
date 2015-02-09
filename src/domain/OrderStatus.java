package domain;

public enum OrderStatus { EMPTY("No orders present"), FILLED("Orders present"), COMPLETED("Order completed"), PAID("Order paid");
	private String text;

	OrderStatus(String text) {
		this.text = text;
}

	public String toString() {
		return text;
	}
}
