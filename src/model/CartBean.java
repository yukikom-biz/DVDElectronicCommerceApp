package src.model;

import model.ItemBean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CartBean {
        private Map<Integer, model.ItemBean> items = new HashMap<Integer, model.ItemBean>();
        private int total;

    public CartBean(){

    }

    public Map<Integer, ItemBean> getItems() {
        return items;
    }

        public void addCart(model.ItemBean bean, int nums){
        model.ItemBean item = (model.ItemBean)items.get(new Integer(bean.getId()));
        if(item == null){
            bean.setQuality((nums));
            items.put(new Integer((bean.getId())),bean);
        }else {
            item.setQuality(nums + item.getQuality());
        }
        recalcTotal();
    }

        public void deleteCart(int itemCode){
        items.remove(new Integer((itemCode)));
        recalcTotal();
    }

        public int getTotal(){
        return total;
    }

        private void recalcTotal() {
        total = 0;
        Collection<model.ItemBean> list = items.values();
        for(model.ItemBean item : list){
            total += item.getPrice() * item.getQuality();
        }
    }
}
