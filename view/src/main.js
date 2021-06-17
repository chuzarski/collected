import { createApp, h } from 'vue'

import HelloWorld from './components/HelloWorld.vue'
import Register from './components/Register.vue'
import Login from './components/Login.vue'


const routes = {
    '/': HelloWorld,
    '/login' : Login,
    '/register': Register
}

//createApp(App).mount('#app').

const Router = {
    data: () => ({
      currentRoute: window.location.pathname
    }),

    computed: {
      CurrentComponent() {
        return routes[this.currentRoute]
      }
    },
  
    render() {
      return h(this.CurrentComponent)
    }
  }

  createApp(Router).mount('#app')