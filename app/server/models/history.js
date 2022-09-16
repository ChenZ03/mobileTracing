const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const historySchema = new Schema({
  icNumber: {type: String},
  location: {type: String},
  dateTime: {type: String},
});

module.exports = mongoose.model('history', historySchema);
