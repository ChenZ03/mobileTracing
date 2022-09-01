const express = require('express');
const router = express.Router();

const History = require('../models/history');

router.get('/all', async (req, res) => {
  try {
    let histories = await History.find({});
    return res.json(histories);
  } catch (e) {
    return res.json({e, msg: 'No histories found'});
  }
});

router.post('/insert', async (req, res) => {
  const {icNumber, location, dateTime} = req.body;

  History.findOne({location}, async (err, exists) => {
    if (exists) {
      try {
        let history = await History.findOneAndUpdate({icNumber}, {location, dateTime});
        return res.json({msg: 'History updated', history});
      } catch (e) {
        return res.status(400).json({msg: 'Error updating history'});
      }
    } else {
      try {
        const history = new History();
        history.icNumber = icNumber;
        history.location = location;
        history.dateTime = dateTime;
        history.save();
        return res.json({msg: 'History inserted', history});
      } catch (e) {
        return res.status(400).json({msg: 'Error inserting history'});
      }
    }
  });
});

module.exports = router;
