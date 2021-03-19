import MetaTreino from '../models/MetaTreino';
import Treino from '../models/Treino';
import '../models/User';

export default {
  async index(req, res) {
    const { id } = req.perso;

    const treinos = await MetaTreino.find({ personal: id }).populate({
      path: 'aluno',
      select: 'name',
    });

    return res.json(treinos);
  },

  async store(req, res) {
    const { id } = req.perso;
    const { treinos } = req.body;

    const treinosArray = await Treino.create(treinos);

    Object.assign(req.body, {
      personal: id,
      treinos: treinosArray.map(treino => treino._id),
    });

    const treino = await MetaTreino.create(req.body);

    return res.json(treino);
  },

  async destroy(req, res) {
    const { id } = req.perso;
    const { id: id_treino } = req.params;

    const treino = await MetaTreino.findOneAndRemove({
      _id: id_treino,
      personal: id,
    });

    if (!treino) {
      return res.status(400).json({ error: 'Treino não encontrado' });
    }

    return res.status(200).send();
  },
};
