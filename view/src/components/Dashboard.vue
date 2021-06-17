<template>

You made it to the dashboard
</template>

<script>

import service from "../serv/index";

// eslint-disable-next-line no-unused-vars
export default {

  
  name: 'Dashboard',
  props: {
    msg: String
  },
  methods:{
    /**
     * List Creation
     */

    createListRequest: function () {

      var authedAxios = service(true)
      var listName = document.getElementById("list-name").value
      var listType = document.getElementById("list-type-select").value

      var addListRequestData = {
        list_name: listName,
        list_type: listType
      }

      // shoot request
      authedAxios.post('/list/add', addListRequestData).then(r => {
        console.log("Got success")
        console.log(r)
      })
    },

    /**
     * List fetching
     */

    fetchSingleListById: function (listId) {
      if (!listId || isNaN(listId)) {
        console.log("Need a listId to fetch")
      }

      authedAxios.get(`/list/${listId}`).then(response => {
      }).catch(e => {

      })
    },

    fetchAllLists: function (listType) {

      var authedAxios = service(true)
      var reqPath = '';

      if(listType == null) {
        console.log("List type not passed")
        return
      }

      if(listType == "COLLECTION") {
        reqPath = '/list/all'
      } else if(listType == "WISHLIST"){
        reqPath = '/list/wishlists'
      } else {
        console.log(`${listType} not a valid list type`)
        return
      }

      // shoot request
      authedAxios.get(reqPath).then(r => {
        if(!onAllListsFetched) {
          console.log("fetchAllLists doesn't have a callback")
          console.log(r.data)
          return
        }
        onAllListsFetched(listType, r.data, r.status)
      }).catch(e => {
      })
    },

    /**
     * Rename List
     */

    handleRenameList: function(listId, name) {
      if(!listId || !name || isNaN(listId)) {
        console.log("listId or name missing")
        return
      }

      authedAxios.post(`/list/${listId}/rename`, {list_name:name}).then(r =>{

      }).catch(e =>{
      })
    },

    /**
     * Remove List
     */

    handleRemoveList: function(listId) {
      if(!listId || isNaN(listId)) {
        console.log("listId missing")
      }

      authedAxios.delete(`/list/${listId}/remove`).then(r =>{

      }).catch(e =>{
        console.log("List not removed. handleRemoveList doesn't have a callback")
      })
    },

    /**
     * List Sorting
     * This will set the sort order on a list and return it sorted, thus a onListFetchedCallback can be passed in
     */
    handleSortList: function(listId, sortOrder){
      if(listId == null || isNaN(listId) || sortOrder == null) {
        console.log("ListId or sortOrder not passed")
        return
      }

      var reqPath = `/list/${listId}/sort/`

      switch(sortOrder) {
        case "DATE":
          reqPath += "DATE"
          break;
        case "DATE_REVERSE":
          reqPath += "DATE_REVERSE"
          break;
        case "RATING":
          reqPath += "RATING"
          break;
        case "RATING_REVERSE":
          reqPath += "RATING_REVERSE"
          break;
        case "NAME_REVERSE":
          reqPath += "NAME_REVERSE"
          break;
        case "NAME":
          reqPath += "NAME"
          break;
        default:
          console.log("Unsupported sort method")
          return
      }
      // shoot request
      authedAxios.post(reqPath).then(r => {
        if(!onAllListsFetched) {
          console.log("handleSortList doesn't have a callback")
          console.log(r.data)
          return
        }
        onAllListsFetched(r.data, r.status)
      }).catch(e => {
        console.log(reqPath)
      })
    },

    /**
     * Item Addition
     */

    handleAddItem: function(parentListId, onItemAddSuccess, onItemAddFail) {
      var itemName = document.getElementById("item-add-name").value
      var itemType = document.getElementById("item-add-type").value
      var itemDescription = document.getElementById("item-add-description").value
      var itemReleaseDate = document.getElementById("item-add-date").value
      var itemRating = document.getElementById("item-add-rating").value

      if(!parentListId == null) {
        console.log("missing parent id")
        return
      }

      var requestData = {
        name: itemName,
        type: itemType,
        description: itemDescription,
        release_date: itemReleaseDate,
        rating: itemRating,
        parent_list: parentListId
      }

      authedAxios.post('/item/add', requestData).then(r => {
        if(!onItemAddSuccess) {
          console.log("Item added, handleAddItem doesn't have a callback!")
          console.log(r)
        }

        onItemAddSuccess(r.data, r.status)
      }).catch(e => {
        onItemAddFail(e)
      })
    },

    /**
     * Item Modification
     */

    handleModifyItem: function(itemId) {
      var name = document.getElementById("item-modify-name").value
      var type = document.getElementById("item-modify-type").value
      var release = document.getElementById("item-modify-date").value

      if(!itemId) {
        console.log("Item ID not passed")
        return;
      }

      var reqData = {
        name: name,
        type: type,
        release_date: release
      }

      authedAxios.post(`/item/${itemId}/modify`, reqData).then(r =>{

      })
    },

    /**
     * Item Description Update
     */

    handleItemDescriptionUpdate: function(itemId) {
      if(!itemId || isNaN(itemId)) {
        console.log("Missing itemId")
      }

      var itemDescription = document.getElementById("item-description-update").value

      authedAxios.post(`/item/${itemId}/description`, {description: itemDescription}).then(r => {

      })
    },

    /**
     * Item Rating updated
     */
    handleItemRating: function(itemId, rating) {
      if(!itemId || !rating) {
        console.log("itemId or rating not passed")
        return
      }

      authedAxios.post(`/item/${itemId}/rating/${rating}`).then(r => {

      })
    },

    /**
     * item fetching
     */

    handleFetchItem: function(itemId, onItemFetchSuccessful, onItemFetchFail) {
      if(!itemId == null) {
        console.log("itemId not passed")
        return
      }

      authedAxios.get(`/item/${itemId}`).then(r => {
        if(onItemFetchSuccessful) {
          onItemFetchSuccessful(r.data, r.status)
        }
      }).catch(e => {
        if(!onItemFetchFail) {
          console.log("Failed to fetch item")
        }
        onItemFetchFail(e)
      })
    },

    /**
     * handleDeleteItem
     */

    handleDeleteItem: function(itemId, onItemDeleteSuccessful) {
      if(!itemId) {
        console.log("Dont't have an itemId")
        return
      }

      authedAxios.delete(`/item/${itemId}/remove`).then(r => {
        if(onItemDeleteSuccessful) {
          onItemDeleteSuccessful(r.data, r.status)
        }
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
