const axios = require('axios')

axios.post('http://localhost:3001/instrumentos',{
    "id" : "I78",
    "#text" : "Pífaro"
}).then(function (resp){
    console.log(resp.headers.location)
})
.catch(function(error){
    console.log("Erro: " + error)
})