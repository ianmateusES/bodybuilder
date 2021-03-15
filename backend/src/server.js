import express from 'express';
import App from './App';

const server = express();

server.use(App);

server.listen(3333, () => {
  console.log('🚀 Bodybuilder - Server started on port 3333');
});
