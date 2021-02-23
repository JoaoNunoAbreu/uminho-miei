var express = require('express');
var router = express.Router();
const Compra = require('../controllers/compra')
var jwt = require('jsonwebtoken')

router.get('/token', function(req,res){
  var sub = "Exame"
  var data = "dataDoSistema"
  const token = jwt.sign({sub,data}, "DAW2020-recurso",{
      expiresIn: 3600 // expires in 1h
    });
    return res.status(200).json({ token: token });
  }
)

// Listar todos os Compras
router.get('/listas', function(req, res) {
  Compra.listar()
    .then(dados => res.status(200).jsonp(dados) )
    .catch(e => res.status(500).jsonp({error: e}))
  
});

router.get('/categorias', function(req, res) {
  Compra.categorias()
    .then(dados => res.status(200).jsonp(dados) )
    .catch(e => res.status(500).jsonp({error: e}))
});

router.get('/categorias/:id/produtos', function(req, res) {
  Compra.categoriasID(req.params.id)
    .then(dados => res.status(200).jsonp(dados) )
    .catch(e => res.status(500).jsonp({error: e}))
});

router.get('/listas/:id', function(req, res) {
  Compra.consultar(req.params.id)
    .then(dados => res.status(200).jsonp(dados) )
    .catch(e => res.status(500).jsonp({error: e}))
});

router.post('/listas', (req, res) => {
  Compra.insert(req.body)
    .then(data => res.status(200).json({message: data._id}))
    .catch(e => res.status(404).jsonp({error: e})) 
});

router.post('/listas/:id', (req, res) => {
  Compra.insertProd(req.body,req.params.id)
    .then(data => res.status(200).json({message: data._id}))
    .catch(e => res.status(404).jsonp({error: e})) 
});

router.delete('/listas/:idLista/produtos/:idProd', (req,res) => {
  console.log(req.params.idLista)
  console.log(req.params.idProd)
  Compra.removeProd(req.params.idLista,req.params.idProd)
    .then(data => {
      console.log(data)
      if(data.nModified == 0)
        res.status(200).json({message: false})
      else
      res.status(200).json({message: true})
    })
    .catch(e => res.status(404).jsonp({message: e}))
})

router.delete('/listas/:id', (req,res) => {
  Compra.remove(req.params.id)
    .then(data => {
      console.log(data)
      if(data.n == 0)
        res.status(200).json({message: false})
      else
      res.status(200).json({message: true})
    })
    .catch(e => res.status(404).jsonp({message: false}))
})

module.exports = router;
