import { Router } from 'express';
import { celebrate, Segments, Joi } from 'celebrate';
import ensureAuthenticated from '../app/middleware/ensureAuthenticated';
import TreinoAlunoController from '../app/controllers/TreinoAlunoController';

// http://localhost:3334/treino-aluno
const exercicioRouter = Router();

exercicioRouter.use(ensureAuthenticated);

exercicioRouter.get(
  '/:id_aluno',
  celebrate({
    [Segments.PARAMS]: {
      id_aluno: Joi.string().required(),
    },
  }),
  TreinoAlunoController.index,
);

exercicioRouter.get(
  '/:id_aluno/treino/:id_treino',
  celebrate({
    [Segments.PARAMS]: {
      id_aluno: Joi.string().required(),
      id_treino: Joi.string().required(),
    },
  }),
  TreinoAlunoController.show,
);

exercicioRouter.delete(
  '/:id_aluno/treino/:id_treino',
  celebrate({
    [Segments.PARAMS]: {
      id_aluno: Joi.string().required(),
      id_treino: Joi.string().required(),
    },
  }),
  TreinoAlunoController.destroy,
);

exercicioRouter.put(
  '/:id_aluno/treino/:id_treino',
  celebrate({
    [Segments.BODY]: {
      objective: Joi.string(),
      treinos: Joi.array()
        .min(3)
        .items(
          Joi.object({
            _id: Joi.string().allow(''),
            division: Joi.string().required(),
            exercise: Joi.string().required(),
            methodology: Joi.string().required(),
            series: Joi.number().required(),
            repetitions: Joi.number().required(),
            comments: Joi.string().allow(''),
          }),
        ),
    },
    [Segments.PARAMS]: {
      id_aluno: Joi.string().required(),
      id_treino: Joi.string().required(),
    },
  }),
  TreinoAlunoController.update,
);

export default exercicioRouter;
