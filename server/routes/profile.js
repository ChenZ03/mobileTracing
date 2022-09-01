const express = require('express');
const router = express.Router();

const User = require('../models/user');

router.get('/:icNumber', async (req, res) => {
  try {
    let user = await User.findOne({icNumber: req.params.icNumber});
    return res.json(user);
  } catch (e) {
    return res.json({e, msg: 'User not found'});
  }
});

router.put('/:icNumber', async (req, res) => {
  const {newName, newPhone} = req.body;
  try {
    let user = await User.findOneAndUpdate({icNumber: req.params.icNumber}, {username: newName, phone: newPhone});
    return res.json({msg: 'Profile updated successfully', user});
  } catch (err) {
    return res.json({msg: 'User not found'});
  }
});
module.exports = router;
