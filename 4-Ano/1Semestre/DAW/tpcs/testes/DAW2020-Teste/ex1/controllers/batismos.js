var Batismo = require('../models/batismos')

module.exports.listar = () => {
    return Batismo
        .find({},{_id:1,date:1,title:1,ref:1})
        .exec()
}

module.exports.listporAno = a => {
    return Batismo
        .find({"date" : {$regex : a}})
        .exec()
}

module.exports.consultar = id =>{
    return Batismo
        .find({_id :id})
        .exec()
}

module.exports.progenitores = () => {
    return Batismo
        .find({}, {_id: 1, pai: 1, mae: 1 })
        .exec()
}

module.exports.batisado = () =>{
    return Batismo.aggregate([
        { $addFields: {
            "lista": { $regexFind: { input: "$title", regex: "(?<=: ).*(?=\. Pai)" } }
         }
        },
        {$sort: {lista: 1}},
        {$group:
            {_id:null,
            batisados:{$push:{id:"$_id",batisado:"$lista.match"}}
            }
        },
        {
        $project: {
            _id:0,
          batisados:1
        }}
      
    ]).exec();
}

module.exports.stats = () =>{
    return Batismo.aggregate( [
        { $group: { _id: "$ano" ,  count: { $sum: 1 } } },
        { $project: { _id: 1, count: 1 } }
    ])
}