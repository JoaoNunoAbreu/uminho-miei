// Criar e descodificar um token

var jwt = require('jsonwebtoken')

try{
    var token = jwt.sign({username: 'jcr', level: 'editor', expiresIn:"1d"},'DAW2020')
    console.log('Token: ' + token)
}
catch(e){
    console.log('Erro na criação do token: ' + e)
}

try{
    var decoded = jwt.verify(token,'segredo errado')
    console.log(JSON.stringify(decoded))
}
catch(e){
    console.log('Erro: ' + e)
}

jwt.verify(token,'DAW2020',function(e,payload){
    if(e) console.log('Erro na verificação do token: ' + e)
    else console.log('Descodificado: ' + JSON.stringify(payload))
})