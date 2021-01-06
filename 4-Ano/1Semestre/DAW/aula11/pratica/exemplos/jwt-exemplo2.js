// Criar e descodificar um token com chaves RSA

var fs = require('fs')
var jwt = require('jsonwebtoken')
var privateKey = fs.readFileSync('mykey.pem')

var token = jwt.sign({username:'jcr'}, privateKey, {algorithm: 'RS256'})
console.log('Token: ' + token + '\n\n')

fs.readFile('pubkey.pem',function(e,publicKey){
    jwt.verify(token,publicKey,{algorithms: ['RS256']}, function(e,decoded){
        if(e) console.log('Erro: ' + e)
        else console.log('DATA: ' + JSON.stringify(decoded))
    })
})