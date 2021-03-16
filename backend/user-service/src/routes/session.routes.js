import { Router } from 'express';
import { celebrate, Segments, Joi } from 'celebrate';
import SessionController from '../app/controllers/SessionController';

// http://localhost:3333/sessions
const sessionsRouter = Router();

sessionsRouter.post(
  '/',
  celebrate({
    [Segments.BODY]: {
      email: Joi.string().email().required(),
      password: Joi.string().required(),
    },
  }),
  SessionController.store,
);

export default sessionsRouter;
