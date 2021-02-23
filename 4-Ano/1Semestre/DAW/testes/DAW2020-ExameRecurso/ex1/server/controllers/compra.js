// Controlador para o modelo Compra
var Compra = require('../models/compra')

// Devolve a lista de Compras
module.exports.listar = () => {
    return Compra.aggregate([
        {
            $project: {
                _id: 1,
                data: 1,
                designacao:1,
                quantidade: { $cond: { if: { $isArray: "$produtos" }, then: { $size: "$produtos" }, else: "NA"} }
            }
        }
    ])
    .exec()
}

module.exports.consultar = idb => {
    return Compra
        .findOne({_id: idb})
        .exec()
}


module.exports.categorias = () => {
    return Compra.distinct("produtos.categoria").sort().exec()
}

module.exports.categoriasID = () => {
    //return Compra.distinct("produtos.categoria").sort().exec()
}


module.exports.insert = c => {
    var novo = new Compra(c)
    return novo.save()
}

module.exports.insertProd = (p,id) => {
    return Compra.updateOne(
        { _id: id }, 
        { $push: { produtos: p } }
    );
}

module.exports.remove = id => {
    return Compra
        .deleteOne({_id: id})
        .exec()
}

module.exports.removeProd = (idLista,idProd) => {
    return Compra.updateOne(
        { _id: idLista },
        { $pull: { "produtos" : { _id: idProd } } }
    );
}