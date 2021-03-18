import 'dotenv/config';
import express from 'express';
import App from './App';

const server = express();

server.use(App);

server.listen(process.env.PORT_APPLICATION || 3333, () => {
  console.log('🚀 Bodybuilder - User - Server started on port 3333');
});
