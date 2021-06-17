import axios from 'axios'

export default({authed = false} = {}) => {
    const options = {
        baseURL: 'http://collected.chuzarski.net:8080'
    }

    if(authed) {
        options.headers.Authorization = 'Bearer ' + localStorage.getItem("bearer_token")
    }

    const instance = axios.create(options)
    return instance;
}