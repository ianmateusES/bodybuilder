import { Router } from 'express';
import { celebrate, Segments, Joi } from 'celebrate';
import ProfileController from '../app/controllers/ProfileController';
import ensureAuthenticated from '../app/middleware/ensureAuthenticated';

// http://localhost:3334/profile
const profileRouter = Router();

profileRouter.use(ensureAuthenticated);
profileRouter.get('/', ProfileController.show);

profileRouter.put(
  '/',
  celebrate({
    [Segments.BODY]: {
      name: Joi.string(),
      email: Joi.string().email(),
      old_password: Joi.string(),
      password: Joi.when('old_password', {
        is: Joi.exist(),
        then: Joi.required(),
      }),
      password_confirmation: Joi.when('password', {
        is: Joi.exist(),
        then: Joi.valid(Joi.ref('password')).required(),
      }),
      birthday: Joi.date(),
      telephone: Joi.string(),
      cref: Joi.string(),
      address: Joi.string(),
    },
  }),
  ProfileController.update,
);

export default profileRouter;
