import MetaTreino from '../models/MetaTreino';
import Treino from '../models/Treino';
import User from '../models/User';

export default {
  async index(req, res) {
    const { id } = req.perso;
    const { id_aluno } = req.params;

    const user = await User.findById(id_aluno);

    if (!user) {
      return res.status(401).json({ error: 'Aluno não existe' });
    }

    const treinos = await MetaTreino.find({
      personal: id,
      aluno: id_aluno,
    });

    return res.json(treinos);
  },

  async show(req, res) {
    const { id } = req.perso;
    const { id_aluno, id_treino } = req.params;

    const treino = await MetaTreino.findOne({
      personal: id,
      _id: id_treino,
      aluno: id_aluno,
    }).populate({
      path: 'treinos',
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
    let { treinos: updateTreinos } = req.body;

    const metaTreino = await MetaTreino.findOne({
      _id: id_treino,
      personal: id,
      aluno: id_aluno,
    });

    if (!metaTreino) {
      return res.status(400).json({ error: 'Treino não encontrado' });
    }

    const updateTreinosId = updateTreinos.map(treino => treino._id);

    const treinosIdRemove = metaTreino.treinos.filter(
      treino => !updateTreinosId.includes(String(treino._id)),
    );

    const createTreino = [];

    updateTreinos = updateTreinos.filter(treino => {
      if (!treino._id) {
        const { _id, ...body } = treino;
        createTreino.push(body);
        return false;
      }
      return true;
    });

    const addTreino = await Treino.create(createTreino);

    updateTreinos = updateTreinos.concat(addTreino);

    Object.assign(req.body, { treinos: updateTreinos });

    const newTreino = await MetaTreino.findOneAndUpdate(
      { _id: id_treino, personal: id },
      req.body,
      {
        new: true,
      },
    );

    if (!newTreino) {
      return res.status(401).json({ error: 'Atualização não realizada' });
    }

    await Treino.deleteMany({ _id: treinosIdRemove });

    return res.json(newTreino);
  },

  async destroy(req, res) {
    const { id } = req.perso;
    const { id_aluno, id_treino } = req.params;

    const treino = await MetaTreino.findOneAndRemove({
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
