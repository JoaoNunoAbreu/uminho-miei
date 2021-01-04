const axios = require('axios')

axios.get('http://localhost:3001/cursos')
.then(function (resp){
    var cursos = resp.data
    for(var i = 0; i < cursos.length; i++){
        axios.post('http://localhost:3000/pubs',{
            "id" : cursos[i].id,
            "title" : cursos[i].designacao,
            "year" : "2020"
        }).then(function (resp){
            console.log("Inseri o registo: " + resp.data.id)
        })
        .catch(function(error){
            console.log("Erro no POST: " + error)
        })
    }
})
.catch(function(error){
    console.log("Erro no GET: " + error)
})