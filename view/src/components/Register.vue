<template>

<h1>Register</h1>

<form method="dialog">
    <input type="text" name="" id="" placeholder="Username"><br>
    <input type="text" name="" id="" placeholder="Email"><br>
    <input type="text" name="" id="" placeholder="Password"><br>
    <button class="button-primary">Create Account</button>
</form>


</template>

<script>

// thank you https://stackoverflow.com/questions/61874994/vue-disable-no-unused-vars-error-the-simplest-fix
// eslint-disable-next-line no-unused-vars
import serv from '../serv/index'

export default {
  name: 'Register',
  props: {
    msg: String
  }
}


</script>

<style scoped>
button {
    margin: 5px;
}
</style>