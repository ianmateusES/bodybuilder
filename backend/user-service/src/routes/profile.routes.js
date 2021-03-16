import { Router } from 'express';
import ProfileController from '../app/controllers/ProfileController';
import ensureAuthenticated from '../app/middleware/ensureAuthenticated';

// http://localhost:3333/profile
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
      address: Joi.string(),
      number: Joi.when('address', {
        is: Joi.exist(),
        then: Joi.number().required(),
      }),
      city: Joi.string(),
      telephone: Joi.string().max(11).min(9),
    },
  }),
  ProfileController.update,
);

export default profileRouter;
