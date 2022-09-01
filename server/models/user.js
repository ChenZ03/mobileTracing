const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const userSchema = new Schema({
  icNumber: {type: String},
  password: {type: String},
  username: {type: String},
  age: {type: Number},
  phone: {type: String},
});

module.exports = mongoose.model('user', userSchema);
