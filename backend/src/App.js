import 'dotenv/config';
import { Router } from 'express';
import cors from 'cors';
import { errors } from 'celebrate';
import routes from './routes';

const App = Router();

App.use(cors()); // Config conection service
App.use(express.json()); // Request body in json

App.use(routes);

App.use(errors());

export default App;
