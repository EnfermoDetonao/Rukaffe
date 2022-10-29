package Database;

import Models.Inventory;
import java.util.List;

public class QueryContract {

    public interface InventoryQuery {
        void createItem(Inventory inventory, QueryResponse<Boolean> response);
        void readItem(int InventoryId, QueryResponse<Inventory> response);
        void readAllItem(QueryResponse<List<Inventory>> response);
        void updateItem(Inventory inventory, QueryResponse<Boolean> response);
        void deleteItem(int InventoryId, QueryResponse<Boolean> response);
    }

}