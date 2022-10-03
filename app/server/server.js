require('dotenv').config();
const {PORT, DB_URL} = process.env;
const express = require('express');
const app = express();
const cors = require('cors');
const mongoose = require('mongoose');


//Check DB Connection
mongoose.connect(`mongodb+srv://pegion:pigeonflyfly@cluster0.wbndh.mongodb.net/mobileTracing?retryWrites=true&w=majority`);
mongoose.connection.once('open', () => console.log('Connected to MongoDB'));

app.use(cors());
app.use(express.json());

app.use('/auth', require('./routes/auth'));
app.use('/profile', require('./routes/profile'));
app.use('/history', require('./routes/history'));

app.listen(PORT, () => console.log(`Server is running in port ${PORT}`));
