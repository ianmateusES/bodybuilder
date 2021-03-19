import { Router } from 'express';
import { celebrate, Segments, Joi } from 'celebrate';
import ensureAuthenticated from '../app/middleware/ensureAuthenticated';
import ExercicioController from '../app/controllers/ExercicioController';

// http://localhost:3334/exercicio
const exercicioRouter = Router();

exercicioRouter.use(ensureAuthenticated);
exercicioRouter.post(
  '/',
  celebrate({
    [Segments.BODY]: {
      id: Joi.string().required(),
    },
  }),
  ExercicioController.store,
);
exercicioRouter.get('/me', ExercicioController.index);
exercicioRouter.delete(
  '/me/:id',
  celebrate({
    [Segments.PARAMS]: {
      id: Joi.string().required(),
    },
  }),
  ExercicioController.destroy,
);

export default exercicioRouter;
