const mongoose = require('mongoose')

var quantidadeSchema = new mongoose.Schema({
  valor: String,
  unidade: String,
})

var produtoSchema = new mongoose.Schema({
  designacao: String,
  categoria: String,
  quantidade: quantidadeSchema
})

var compraSchema = new mongoose.Schema({
  designacao: String,
  data: String,
  produtos: [produtoSchema]
});

module.exports = mongoose.model('compra', compraSchema)