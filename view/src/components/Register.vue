<template>
  <h1>Register</h1>

  <form method="dialog">
    <input type="text" name="" id="reg-username" placeholder="Username" /><br />
    <input type="text" name="" id="reg-email" placeholder="Email" /><br />
    <input type="text" name="" id="reg-password" placeholder="Password" /><br />
    <button v-on:click="handleRegister" class="button-primary">Create Account</button>
  </form>
</template>

<script>
// thank you https://stackoverflow.com/questions/61874994/vue-disable-no-unused-vars-error-the-simplest-fix
// eslint-disable-next-line no-unused-vars
import service from "../serv/index";

export default {
  name: "Register",
  props: {
    msg: String,
  },
  methods: {
    handleRegister: function () {

    var axiosInstance = service()
      var username = document.getElementById("reg-username").value;
      var email = document.getElementById("reg-email").value;
      var password = document.getElementById("reg-password").value;

      if (username == null || email == null || password == null) {
        console.log("registration form has a blank field");
        return;
      }

      var details = {
        username: username,
        email: email,
        password: password,
      };

      axiosInstance
        .post("/register", details)
        .then(() => {
            window.location.pathname = '/login'
        })
    },
  },
};
</script>

<style scoped>
button {
  margin: 5px;
}
</style>