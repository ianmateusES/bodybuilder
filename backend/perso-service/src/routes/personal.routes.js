import { Router } from 'express';
import { celebrate, Segments, Joi } from 'celebrate';
import PersonalController from '../app/controllers/PersonalController';

// http://localhost:3334/personais
const personaisRouter = Router();

personaisRouter.post(
  '/',
  celebrate({
    [Segments.BODY]: {
      name: Joi.string().required(),
      email: Joi.string().email().required(),
      password: Joi.string().min(6).required(),
      password_confirmation: Joi.string().required().valid(Joi.ref('password')),
      birthday: Joi.date().required(),
      telephone: Joi.string().max(11).min(9).required(),
      cref: Joi.string().required(),
      address: Joi.string().required(),
    },
  }),
  PersonalController.store,
);

export default personaisRouter;
