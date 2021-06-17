<template>
<div style="border:1px solid black; display: inline-block;padding: 15px; border-radius: 25px;">
    <label style="padding-right: 15px;" v-model="projectname">Name</label>
    <button>Add</button>
    <button>Edit</button>
    <button>Delete</button>
    <button>View</button>
</div>
</template>

<script>
export default {
  name: 'Dashboard',
  props: {
    msg: String
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
