const express = require('express');
const router = express.Router();

const User = require('../models/user');

// REGISTER
router.post('/register', (req, res) => {
  const {icNumber, password} = req.body;
  if (password.length === 0) return res.status(400).json({msg: 'Please input a password'});
  if (password.length < 8) return res.status(400).json({msg: 'Password should be more than 8 characters'});

  User.findOne({icNumber}, (err, exists) => {
    if (exists) {
      return res.status(400).json({msg: 'User already exists'});
    } else {
      const user = new User();
      user.icNumber = icNumber;
      user.password = password;
      user.save();
      return res.json({
        msg: 'Registered successfully',
        user,
      });
    }
  });
});

//LOGIN
router.post('/login', (req, res) => {
  const {icNumber, password} = req.body;

  User.findOne({icNumber}, (err, user) => {
    if (!user) {
      return res.status(400).json({msg: 'User doesnt exist'});
    }
    let isMatch = password == user.password;

    if (!isMatch) return res.status(400).json({msg: 'Invalid Credentials'});

    if (isMatch)
      return res.json({
        msg: 'Logged in successfully',
      });
  });
});

module.exports = router;
