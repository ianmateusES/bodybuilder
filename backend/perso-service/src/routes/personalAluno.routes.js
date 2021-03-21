import { Router } from 'express';
import { celebrate, Segments, Joi } from 'celebrate';
import ensureAuthenticated from '../app/middleware/ensureAuthenticated';
import PersonalAlunoController from '../app/controllers/PersonalAlunoController';

// http://localhost:3334/personal-alunos
const pesonalAlunoRouter = Router();

pesonalAlunoRouter.use(ensureAuthenticated);

pesonalAlunoRouter.post(
  '/',
  celebrate({
    [Segments.BODY]: {
      name: Joi.string().required(),
      email: Joi.string().email().required(),
      password: Joi.string().required(),
      password_confirmation: Joi.string().required().valid(Joi.ref('password')),
      birthday: Joi.date().required(),
    },
  }),
  PersonalAlunoController.store,
);

pesonalAlunoRouter.get(
  '/',
  celebrate({
    [Segments.QUERY]: {
      status: Joi.boolean(),
    },
  }),
  PersonalAlunoController.index,
);

export default pesonalAlunoRouter;
