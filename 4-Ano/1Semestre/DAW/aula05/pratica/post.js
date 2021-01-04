const axios = require('axios')

axios.post('http://localhost:3000/pubs',{
    "id" : "DAW2020",
    "title" : "Aula5",
    "year" : "2020"
}).then(function (resp){
    console.log(resp.data)
})
.catch(function(error){
    console.log("Erro: " + error)
})