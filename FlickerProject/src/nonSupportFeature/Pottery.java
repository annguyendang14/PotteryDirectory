package nonSupportFeature;
/**
 * 
 * @author Amiao
 * Description: The Pottery class
 *
 */

public class Pottery {
	private String color;
	private String description;
	private String size;
	private int quantity;

	public Pottery(String color, String description, String size, int quantity) {
		setColor(color);
		setDescription(description);
		setSize(size);
		setQuantity(quantity);
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getColor() {
		return color;
	}

	public String getDescription() {
		return description;
	}

	public String getSize() {
		return size;
	}

	public int getQuantity() {
		return quantity;
	}
}
