import { Router } from 'express';
import ensureAuthenticated from '../app/middleware/ensureAuthenticated';
import ExercicioController from '../app/controllers/ExercicioController';

// http://localhost:3334/exercicio
const exercicioRouter = Router();

exercicioRouter.use(ensureAuthenticated);
exercicioRouter.post('/', ExercicioController.store);
exercicioRouter.get('/me', ExercicioController.index);

export default exercicioRouter;
