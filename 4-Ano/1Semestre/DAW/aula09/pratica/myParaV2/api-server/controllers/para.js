// Paragraph controller
var Paragraph = require('../models/para')

// Returns paragraph list
module.exports.list = () => {
    return Paragraph.find().exec()
}

// Returns a paragrah record
module.exports.lookup = id => {
    return Paragraph.findOne({_id: id}).exec()
}

// Inserts a new paragraph
module.exports.insert = p => {
    console.log(JSON.stringify(p))
    return new Paragraph(p).save()
}

// Removes a paragraph
module.exports.remove = id => {
    return Paragraph.deleteOne({_id: id}).exec()
}

// Edit one paragraph
module.exports.edit = function(id,newContent){
    return Paragraph.findByIdAndUpdate(id,newContent,{new: true})
}


