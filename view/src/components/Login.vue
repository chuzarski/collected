<template>
  <h1>Collected</h1>

  <form method="dialog">
    <input type="text" name="" id="login-username" placeholder="Username" /><br />
    <input type="password" name="" id="login-password" placeholder="Password" /><br />
    <button v-on:click="handleLogin"  class="button-primary">Login</button>
    <button v-on:click="navRegister" class="button">Register</button>
  </form>
</template>

<script>
// thank you https://stackoverflow.com/questions/61874994/vue-disable-no-unused-vars-error-the-simplest-fix
// eslint-disable-next-line no-unused-vars
import service from "../serv/index";

export default {
  name: "Login",
  props: {
    msg: String,
  },
  methods: {
    storeBearerToken: function (token) {
      if (!token) {
        console.log("Got a bad token, not storing that");
        return;
      }

      console.log("storing bearer token");

      // overwrite any local storage tokens we may have
      localStorage.setItem("bearer_token", token);
    },
    navDashboard: function() {
        window.location.pathname = '/dashboard'
    }, 
    navRegister: function() {
        console.log("Going to register")
        window.location.pathname = '/register'
    },
    handleLogin: function () {

      var axiosInstance = service()
      var user = document.getElementById("login-username").value;
      var pass = document.getElementById("login-password").value;

      if (user == null || pass == null) {
        console.log("Handle empty login form event here");
        return;
      }

      var credentials = {
        username: user,
        password: pass,
      };
      // shoot request
      axiosInstance.post("/login", credentials).then((r) => {
          // get the token, store it
          var token = r.data.access_token;
          this.storeBearerToken(token);
            console.log(r);
            this.navDashboard()
            return;
          }
        )
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
button {
  margin: 5px;
}
</style>
