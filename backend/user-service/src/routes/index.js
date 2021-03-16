import { Router } from 'express';

import userRouter from './user.routes';
import profileRouter from './profile.routes';
import sessionRouter from './session.routes';

const routes = Router();

routes.use('/users', userRouter);
routes.use('/sessions', sessionRouter);
routes.use('/profile', profileRouter);

export default routes;
