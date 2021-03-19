import { Router } from 'express';
import { celebrate, Segments, Joi } from 'celebrate';
import ensureAuthenticated from '../app/middleware/ensureAuthenticated';
import TreinoController from '../app/controllers/TreinoController';

// http://localhost:3334/treino
const treinoRouter = Router();

treinoRouter.use(ensureAuthenticated);

treinoRouter.post(
  '/',
  celebrate({
    [Segments.BODY]: {
      aluno: Joi.string().required(),
      objective: Joi.string().required(),
      exercise_list: Joi.array()
        .min(3)
        .items(
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

export default treinoRouter;
