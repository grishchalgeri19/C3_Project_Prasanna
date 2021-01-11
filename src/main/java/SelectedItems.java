import java.util.ArrayList;

public class SelectedItems {
    private ArrayList<Item> items;
    private String itemName;
    private int price;

    public SelectedItems() {
        this.items = new ArrayList<>();
    }

    public void addSelectedItems(Item item){
        items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getTotalValueOfSelectedItems(){
        int totalValue = 0;
        if(items.size() > 0) {
            for (Item i : items) {
                totalValue += i.getPrice();
            }
            return totalValue;
        } else {
            return 0;
        }

    }

    public String getItemName() {
        return itemName;
    }

    public int getPrice() {
        return price;
    }
}
