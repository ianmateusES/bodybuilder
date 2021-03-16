import { Router } from 'express';
import ProfileController from '../app/controllers/ProfileController';
import ensureAuthenticated from '../app/middleware/ensureAuthenticated';

// http://localhost:3333/profile
const profileRouter = Router();

profileRouter.use(ensureAuthenticated);
profileRouter.get('/me', ProfileController.show);

export default profileRouter;
