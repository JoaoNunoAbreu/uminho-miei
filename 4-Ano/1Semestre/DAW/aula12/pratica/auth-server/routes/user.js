var express = require('express');
var router = express.Router();
var jwt = require('jsonwebtoken')
var passport = require('passport')
  
router.post('/login', passport.authenticate('local'), function(req, res){
  jwt.sign()
})

module.exports = router;