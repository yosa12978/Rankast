

Vue.component("doc-header", {
    template: '<div><nav><img id="hImg" src="/img/1.PNG" />'
                +'<a id="headerA" class = "btn btn-outline-warning" href="index">Home</a>'
                +'<a id="headerA" class = "btn btn-outline-warning" href="search">Search</a>'
                +'<a id="headerA" class = "btn btn-outline-warning" href="search">Search</a>'
                +'<a id="headerA" class = "btn btn-outline-warning" href="search">Search</a>'
                +'<a id="headerA" class = "btn btn-outline-warning" href="search">Search</a>'
                +'</nav><hr></div>'
});

var header = new Vue({
    el: "#header",
    template: '<doc-header/>'
});