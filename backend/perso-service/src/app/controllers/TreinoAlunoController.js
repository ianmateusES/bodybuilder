import Treino from '../models/Treino';
import ExecucaoExercicio from '../models/ExecucaoExercicio';
import User from '../models/User';

export default {
  async index(req, res) {
    const { id } = req.perso;
    const { id_aluno } = req.params;

    const user = await User.findById(id_aluno);

    if (!user) {
      return res.status(401).json({ error: 'Aluno não existe' });
    }

    const treinos = await Treino.find({
      personal: id,
      aluno: id_aluno,
    });

    return res.json(treinos);
  },

  async show(req, res) {
    const { id } = req.perso;
    const { id_aluno, id_treino } = req.params;

    const treino = await Treino.findOne({
      personal: id,
      _id: id_treino,
      aluno: id_aluno,
    }).populate({
      path: 'list_exercicio',
      populate: {
        path: 'exercise',
        select: 'name',
      },
    });

    if (!treino) {
      return res.status(400).json({ error: 'Treino não encontrado' });
    }

    return res.json(treino);
  },

  async update(req, res) {
    const { id } = req.perso;
    const { id_aluno, id_treino } = req.params;
    let { exercise_list: updateExercise_list } = req.body;

    const treino = await Treino.findOne({
      _id: id_treino,
      personal: id,
      aluno: id_aluno,
    });

    if (!treino) {
      return res.status(400).json({ error: 'Treino não encontrado' });
    }

    const id_updateExercise_list = updateExercise_list.map(
      exercise => exercise._id,
    );

    const exercise_listIdRemove = treino.exercise_list.filter(
      exercise => !id_updateExercise_list.includes(String(exercise._id)),
    );

    const createExecucao_Exercicio = [];

    updateExercise_list = updateExercise_list.filter(execucao_Exercicio => {
      if (!execucao_Exercicio._id) {
        const { _id, ...body } = execucao_Exercicio;
        createExecucao_Exercicio.push(body);
        return false && _id;
      }
      return true;
    });

    const addExecucao_Exercicio = await ExecucaoExercicio.create(
      createExecucao_Exercicio,
    );

    updateExercise_list = updateExercise_list.concat(addExecucao_Exercicio);

    Object.assign(req.body, { exercise_list: updateExercise_list });

    const newTreino = await Treino.findOneAndUpdate(
      { _id: id_treino, personal: id },
      req.body,
      {
        new: true,
      },
    );

    if (!newTreino) {
      return res.status(401).json({ error: 'Atualização não realizada' });
    }

    await ExecucaoExercicio.deleteMany({ _id: exercise_listIdRemove });

    return res.json(newTreino);
  },

  async destroy(req, res) {
    const { id } = req.perso;
    const { id_aluno, id_treino } = req.params;

    const treino = await Treino.findOneAndRemove({
      _id: id_treino,
      personal: id,
      aluno: id_aluno,
    });

    if (!treino) {
      return res.status(400).json({ error: 'Treino não encontrado' });
    }

    return res.status(200).send();
  },
};
