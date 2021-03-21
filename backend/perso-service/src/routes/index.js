import { Router } from 'express';

import personalRouter from './personal.routes';
import profileRouter from './profile.routes';
import sessionRouter from './session.routes';
import exercicioRouter from './exercicio.routes';
import treinoRouter from './treino.routes';
import treinoAlunoRouter from './treinoAluno.routes';
import personalAlunoRouter from './personalAluno.routes';

const routes = Router();

routes.use('/personais', personalRouter);
routes.use('/sessions', sessionRouter);
routes.use('/profile', profileRouter);
routes.use('/exercicio', exercicioRouter);
routes.use('/treinos', treinoRouter);
routes.use('/treino-aluno', treinoAlunoRouter);
routes.use('/personal-alunos', personalAlunoRouter);

export default routes;
