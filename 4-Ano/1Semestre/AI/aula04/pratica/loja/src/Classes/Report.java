package Classes;

public class Report implements java.io.Serializable {

	private float profit;
	private float avg_profit;
	private String product_most_sold;
	private int quantity_most_sold;

	public Report(float profit, float avg_profit, String product_most_sold, int quantity_most_sold) {
		super();
		this.profit = profit;
		this.avg_profit = avg_profit;
		this.product_most_sold = product_most_sold;
		this.quantity_most_sold = quantity_most_sold;
	}

	public float getProfit() {
		return profit;
	}

	public void setProfit(float profit) {
		this.profit = profit;
	}

	public float getAvg_profit() {
		return avg_profit;
	}

	public void setAvg_profit(float avg_profit) {
		this.avg_profit = avg_profit;
	}

	public String getProduct_most_sold() {
		return product_most_sold;
	}

	public void setProduct_most_sold(String product_most_sold) {
		this.product_most_sold = product_most_sold;
	}

	public int getQuantity_most_sold() {
		return quantity_most_sold;
	}

	public void setQuantity_most_sold(int quantity_most_sold) {
		this.quantity_most_sold = quantity_most_sold;
	}

	@Override
	public String toString() {
		return "Report [profit=" + profit + ", avg_profit=" + avg_profit + ", product_most_sold=" + product_most_sold
				+ ", quantity_most_sold=" + quantity_most_sold + "]";
	}

}
