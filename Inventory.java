import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> itemsList;
    public Inventory(){
        this.itemsList = new ArrayList<Item>();
    }

    public ArrayList<Item> getItemsList() {
        return itemsList;
    }

    public void setItems(ArrayList<Item> itemsList) {
        this.itemsList = itemsList;
    }

    public void addItem(Item item){
        itemsList.add(item);
    }

    public void removeItem(String UPC){
        Item itemToRemove = new Item();
        if(itemsList.size() != 0){
            for(Item i: itemsList){
                if(i.getUPC().equals(UPC)){
                    itemToRemove = i;
                }
                itemsList.remove(itemToRemove);
            }
        }
    }

    public void removeItems(ArrayList<Item> itemsToRemove){
        for(Item i: itemsToRemove){
            itemsList.remove(i);
        }
    }

    public void orderItem(String UPC){
        if(itemsList.size() != 0){
            for(Item i: itemsList){
                if(i.getUPC().equals(UPC)){
                    i.decreaseQuantity();
                }
            }
        }
    }

    public void orderItems(ArrayList<Item> itemsToRemove){
        for(Item i: itemsToRemove){
            i.decreaseQuantity();
        }
    }


    public Item getItem(String UPC){
        if(itemsList.size() != 0){
            for(Item i: itemsList){
                if(i.getUPC().equals(UPC)){
                    return i;
                }
            }
        }
        return new Item();
    }

    @Override
    public String toString(){
        String str = "";
        for(Item i: itemsList){
            str += i; 
        }
        return str;
    }



}
