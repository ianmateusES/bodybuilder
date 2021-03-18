import { Router } from 'express';
import { celebrate, Segments, Joi } from 'celebrate';
import ensureAuthenticated from '../app/middleware/ensureAuthenticated';
import TreinoController from '../app/controllers/TreinoController';

// http://localhost:3334/treinos
const exercicioRouter = Router();

exercicioRouter.use(ensureAuthenticated);
exercicioRouter.post(
  '/',
  celebrate({
    [Segments.BODY]: {
      aluno: Joi.string().required(),
      objective: Joi.string().required(),
      treinos: Joi.array().items(
        Joi.object({
          division: Joi.string().required(),
          exercise: Joi.string().required(),
          methodology: Joi.string().required(),
          series: Joi.number().required(),
          repetitions: Joi.number().required(),
          comments: Joi.string().allow(''),
        }),
      ),
    },
  }),
  TreinoController.store,
);
exercicioRouter.get('/me', TreinoController.index);
exercicioRouter.get(
  '/me/:id',
  celebrate({
    [Segments.PARAMS]: {
      id: Joi.string().required(),
    },
  }),
  TreinoController.show,
);

exercicioRouter.delete(
  '/me/:id',
  celebrate({
    [Segments.PARAMS]: {
      id: Joi.string().required(),
    },
  }),
  TreinoController.destroy,
);

export default exercicioRouter;
