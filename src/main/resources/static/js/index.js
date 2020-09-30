var messageAPI = Vue.resource('/home');


Vue.component("message-item", {
    props: ["message"],
    template: '<div><i>({{message.id}})</i><span>{{message.Message}}</span></div>'
});

Vue.component('message', {
    props: ['messages'],
    template: '<div><div v-for="message in messages" ><message-item :message="message"/></div></div>',
    created: function() {
        messageAPI.get().then(res => res.json().then(data => data.forEach(e => this.messages.push(e) )));
    }
});

var app = new Vue({
    el: '#app',
    template: '<message :messages="Messages"/>',
    data: {
        Messages: []
    }
});
