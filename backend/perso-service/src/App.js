import express from 'express';
import mongoose from 'mongoose';
import cors from 'cors';
import { errors } from 'celebrate';
import routes from './routes';

mongoose.connect(
  `mongodb+srv://${process.env.USER_DATABASE}:${process.env.PASSWORD_DATABASE}@universityproject.wqlng.mongodb.net/bodybuilder?retryWrites=true&w=majority`,
  {
    useUnifiedTopology: true,
    useNewUrlParser: true,
    useCreateIndex: true,
    useFindAndModify: false,
  },
);

const App = express();

App.use(cors()); // Config conection service
App.use(express.json()); // Request body in json

App.use(routes);

App.use(errors());

export default App;
