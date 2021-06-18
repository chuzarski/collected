package csi3370.team2.util;

import csi3370.team2.models.ItemList;
import csi3370.team2.models.ItemListItem;
import org.jdbi.v3.core.result.ResultProducer;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Supplier;

public class ItemListProducer implements ResultProducer<Set<ItemList>> {
    private Set<ItemList> lists;
    private Map<String, List<ItemListItem>> keyedItemLists;

    public ItemListProducer() {
        lists = new HashSet<>();
        keyedItemLists = new HashMap<>();
    }

    public Set<ItemList> bindLists() {
        for (ItemList itemList : lists) {
            itemList.setItems(keyedItemLists.get(itemList.getName()));
        }
        return lists;
    }

    @Override
    public Set<ItemList> produce(Supplier<PreparedStatement> statementSupplier, StatementContext ctx) throws SQLException {
        ResultSet rs;
        rs = statementSupplier.get().executeQuery();

        while (rs.next()) {
            mapRow(rs);
        }
        rs.close();
        return bindLists();
    }

    /**
     * This function will map the current row into the corresponding Map entry
     * @param rs
     */
    private void mapRow(ResultSet rs) throws SQLException {
        if (!keyedItemLists.containsKey(rs.getString("LIST_NAME"))) {
            // initialize ItemList
            initItemListObject(rs);

            // initialize the key
            keyedItemLists.put(rs.getString("LIST_NAME"), new ArrayList<>());
        }
        addItemListItemToKey(rs);
    }

    private void initItemListObject(ResultSet rs) throws SQLException {
        lists.add(new ItemList(
                rs.getInt("ITEM_LIST_ID"),
                rs.getString("LIST_NAME"),
                rs.getString("LIST_TYPE"),
                rs.getString("SORT_PREFERENCE"),
                rs.getInt("OWNER_ID")
                ));

    }

    private void addItemListItemToKey(ResultSet rs) throws SQLException {
        ItemListItem item = new ItemListItem(
                rs.getInt("ITEM_ID"),
                rs.getString("ITEM_NAME"),
                rs.getString("ITEM_TYPE"),
                rs.getString("DESCRIPTION"),
                rs.getDate("RELEASE_DATE"),
                rs.getInt("ITEM_RATING"),
                rs.getInt("ITEM_LIST_ID")
        );

        if (item.getName() == null && item.getType() == null && item.getDescription() == null && item.getReleaseDate() == null)
            return; // don't add this to our list

        keyedItemLists.get(rs.getString("LIST_NAME")).add(item);
    }
}
