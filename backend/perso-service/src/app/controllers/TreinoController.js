import Treino from '../models/Treino';
import ExecucaoExercicio from '../models/ExecucaoExercicio';
import '../models/User';

export default {
  async index(req, res) {
    const { id } = req.perso;

    const treinos = await Treino.find({ personal: id }).populate({
      path: 'aluno',
      select: 'name',
    });

    return res.json(treinos);
  },

  async store(req, res) {
    const { id } = req.perso;
    const { exercise_list } = req.body;

    const execucaoExercicioArray = await ExecucaoExercicio.create(
      exercise_list,
    );

    Object.assign(req.body, {
      personal: id,
      exercise_list: execucaoExercicioArray.map(exercise => exercise._id),
    });

    const treino = await Treino.create(req.body);

    return res.json(treino);
  },

  async destroy(req, res) {
    const { id } = req.perso;
    const { id: id_treino } = req.params;

    const treino = await Treino.findOneAndRemove({
      _id: id_treino,
      personal: id,
    });

    if (!treino) {
      return res.status(400).json({ error: 'Treino não encontrado' });
    }

    return res.status(200).send();
  },
};
