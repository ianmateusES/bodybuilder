import 'dotenv/config';
import express from 'express';

import App from './App';

const server = express();

server.use(App);

server.listen(process.env.PORT_APPLICATION || 3334, () => {
  console.log('🚀 Bodybuilder - Personal - Server started on port 3334');
});
